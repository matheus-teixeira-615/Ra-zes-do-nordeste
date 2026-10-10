package raizesdonordeste.com.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.spi.PersistentIdentifierBag;
import org.springframework.stereotype.Service;
import raizesdonordeste.com.model.*;
import raizesdonordeste.com.repository.AuditoriaRepository;
import raizesdonordeste.com.repository.EstoqueRepository;
import raizesdonordeste.com.repository.PedidoRepository;
import raizesdonordeste.com.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;
    private final AuditoriaRepository auditoriaRepository;
    private final UsuarioRepository usuarioRepository;

    private final FidelidadeService fidelidadeService;
    private final PagamentoExternoService pagamentoExternoService;

    public Pedido atualizarStatus(Long pedidoId, String novoStatus, String emailUsuarioLogado){

        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioLogado)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado: " + emailUsuarioLogado));

        boolean temPermissao = usuario.getRoles().contains(Role.FUNCIONARIO) ||
                usuario.getRoles().contains(Role.ADMIN);

        if (!temPermissao) {
            throw new RuntimeException("Acesso negado: Apenas funcionarios podem alterar o status do pedido.");
        }

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado: " + pedidoId));

        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido criarEProcessarPedido(Pedido pedido){
        if (pedido.getCanalPedido() == null){
            throw new IllegalArgumentException("CanalPedido esta vazio");
        }

        for (ItemPedido item : pedido.getItens()){
            Estoque estoque = estoqueRepository.findByUnidadeIdAndProdutoId(pedido.getUnidadeId(), item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto ID "+ item.getProdutoId() + " não encontrado no estoque"));
            if (estoque.getQuantidade() < item.getQuantidade()){
                throw new RuntimeException("Estoque insuficiente: " + item.getProdutoId());
            }
            estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
            estoqueRepository.save(estoque);
        }

        pedido.setStatus("PENDENTE");
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        boolean pagamentoAprovado = pagamentoExternoService.processarPagmento(pedidoSalvo);

        if (pagamentoAprovado){
            pedidoSalvo.setStatus("PAGO");
            if (pedidoSalvo.getClienteId() != null && pedidoSalvo.getValorTotal() != null){
                fidelidadeService.adicionarPontos(pedidoSalvo.getClienteId(), pedidoSalvo.getValorTotal());
            }
        }
        else{
            pedidoSalvo.setStatus("CANCELADO");
        }
        Pedido pedidoFinal = pedidoRepository.save(pedidoSalvo);

        Auditoria auditoria = new Auditoria();
        auditoria.setAcao("CRIACAO_PEDIDO");
        auditoria.setDetalhes("Pedido ID " + pedidoFinal.getId() + " criado via " + pedidoFinal.getCanalPedido() + " status: " + pedidoFinal.getStatus());
        auditoriaRepository.save(auditoria);
        return pedidoFinal;
    }
}

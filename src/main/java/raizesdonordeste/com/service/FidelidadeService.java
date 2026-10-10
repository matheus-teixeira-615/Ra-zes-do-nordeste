package raizesdonordeste.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import raizesdonordeste.com.model.Fidelidade;
import raizesdonordeste.com.repository.FidelidadeRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FidelidadeService {
    private final FidelidadeRepository fidelidadeRepository;
    public void adicionarPontos(Long usuarioId, BigDecimal valorTotalPedido) {
        int pontosGanhos = valorTotalPedido.divide(BigDecimal.TEN).intValue();
        if (pontosGanhos <= 0) return;

        Fidelidade fidelidade = fidelidadeRepository.findByUsuarioId(usuarioId).orElseGet(()->{
            Fidelidade nova = new Fidelidade();
            nova.setUsuarioId(usuarioId);
            nova.setPontos(0);
            return nova;
        });
        fidelidade.setPontos(fidelidade.getPontos() + pontosGanhos);
        fidelidadeRepository.save(fidelidade);

        System.out.println("[FIDELIDADE] Usuario ID " + usuarioId + " Ganhou " + pontosGanhos + " pontos, total acumulado: " + fidelidade.getPontos());
    }

    public Fidelidade resgatarPontos(Long usuarioId, int pontosParaResgatar, boolean consentimento){
        if(!consentimento){
            throw new RuntimeException("Sem consentimento, sem resgate");
        }
        if (pontosParaResgatar <= 0){
            throw new RuntimeException("Sem pontos para resgatar");
        }

        Fidelidade fidelidade = fidelidadeRepository.findByUsuarioId(usuarioId) . orElseThrow(() -> new RuntimeException("Não há registro de fidelidade para o usuario: " + usuarioId));

        if(fidelidade.getPontos() < pontosParaResgatar){
            throw new RuntimeException("Saldo insuficiente");
        }
        fidelidade.setPontos(fidelidade.getPontos() - pontosParaResgatar);
        Fidelidade atualizado = fidelidadeRepository.save(fidelidade);

        System.out.println("[FIDELIDADE] Usuário ID " + usuarioId + " resgatou " + pontosParaResgatar + " pontos. Saldo atual: " + atualizado.getPontos());
        return atualizado;
    }
}

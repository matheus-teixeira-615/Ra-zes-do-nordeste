package raizesdonordeste.com.service;

import org.springframework.stereotype.Service;
import raizesdonordeste.com.model.Pedido;

@Service
public class PagamentoExternoService {

    public boolean processarPagmento(Pedido pedido){
        System.out.println("[GATEWAY EXTERNO] Conectando com a operadora de pagamento (Mock)...");
        System.out.println("[GATEWAY EXTERNO] Processando pagamento para o pedido ID: " + pedido.getId());
        boolean aprovado = true;

        if (aprovado){
            System.out.println("[GATEWAY EXTERNO] Pagamento APROVADO com sucesso!");
            return true;
        }
        else{
            System.out.println("[GATEWAY EXTERNO] Pagamento RECUSADO pelo operador.");
            return false;
        }
    }

}

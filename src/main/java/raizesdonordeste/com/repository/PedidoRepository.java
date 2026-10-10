package raizesdonordeste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raizesdonordeste.com.model.CanalPedido;
import raizesdonordeste.com.model.Pedido;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCanalPedido(CanalPedido canalPedido);
}

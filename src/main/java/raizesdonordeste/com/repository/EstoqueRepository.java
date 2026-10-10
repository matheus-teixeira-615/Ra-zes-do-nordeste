package raizesdonordeste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raizesdonordeste.com.model.Estoque;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByUnidadeIdAndProdutoId(Long unidadeId, Long produtoId);
}

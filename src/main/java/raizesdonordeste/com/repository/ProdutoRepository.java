package raizesdonordeste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raizesdonordeste.com.model.Produto;

import java.beans.JavaBean;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByUnidadeId(Long unidadeId);
}

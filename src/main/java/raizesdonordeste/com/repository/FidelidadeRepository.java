package raizesdonordeste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raizesdonordeste.com.model.Fidelidade;

import java.util.Optional;

public interface FidelidadeRepository extends JpaRepository<Fidelidade, Long> {
    Optional<Fidelidade> findByUsuarioId(Long usuarioId);
}

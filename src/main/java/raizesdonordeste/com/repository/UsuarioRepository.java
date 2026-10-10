package raizesdonordeste.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raizesdonordeste.com.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

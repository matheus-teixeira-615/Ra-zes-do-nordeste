package raizesdonordeste.com.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fidelidade")
public class Fidelidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long usuarioId;

    @Column(nullable = false)
    private Integer pontos = 0;
}

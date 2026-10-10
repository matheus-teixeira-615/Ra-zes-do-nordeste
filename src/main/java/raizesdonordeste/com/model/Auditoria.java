package raizesdonordeste.com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Data
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private String detalhes;

    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();
}

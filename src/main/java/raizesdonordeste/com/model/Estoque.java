package raizesdonordeste.com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estoques")
@Data
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "unidade_id", nullable = false)
    private Long unidadeId;

    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private Integer quantidade;
}

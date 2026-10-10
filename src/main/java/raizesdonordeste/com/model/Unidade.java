package raizesdonordeste.com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "unidades")
@Data
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

}

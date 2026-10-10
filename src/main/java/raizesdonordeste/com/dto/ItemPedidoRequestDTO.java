package raizesdonordeste.com.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPedidoRequestDTO {
    @NotNull(message = "o ID do produto é obrigatorio")
    private long produtoId;

    @Min(value = 1, message = "A quantidade deve ser superior a zero")
    private Integer quantidade;

    @NotNull(message = "O preço unitario é obrigatorio")
    private Long precoUnitario;
}

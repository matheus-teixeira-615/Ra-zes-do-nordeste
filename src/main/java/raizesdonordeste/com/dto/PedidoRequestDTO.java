package raizesdonordeste.com.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {
    @NotNull(message = "o ID do usuario é obrigatorio")
    private Long usuarioId;

    @NotNull(message = "o pedido deve conter itens")
    private List<ItemPedidoRequestDTO> itens;
}

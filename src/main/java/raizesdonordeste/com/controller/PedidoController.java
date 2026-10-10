package raizesdonordeste.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizesdonordeste.com.model.CanalPedido;
import raizesdonordeste.com.model.Pedido;
import raizesdonordeste.com.repository.PedidoRepository;
import raizesdonordeste.com.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido){
        Pedido novoPedido = pedidoService.criarEProcessarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(@RequestParam(required = false)CanalPedido canal){
        if(canal != null){
            return ResponseEntity.ok(pedidoRepository.findByCanalPedido(canal));
        }
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatusPedido(@PathVariable Long id, @RequestParam String status, @RequestParam String emailUsuario){
        Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, status, emailUsuario);
        return ResponseEntity.ok(pedidoAtualizado);
    }


}

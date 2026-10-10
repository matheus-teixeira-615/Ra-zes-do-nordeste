package raizesdonordeste.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizesdonordeste.com.model.Usuario;
import raizesdonordeste.com.repository.UsuarioRepository;
import raizesdonordeste.com.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository){
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> clientes = usuarioRepository.findAll();
        return ResponseEntity.ok(clientes);
    }
}

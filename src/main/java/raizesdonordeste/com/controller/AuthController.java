package raizesdonordeste.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raizesdonordeste.com.model.Usuario;
import raizesdonordeste.com.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
        Usuario salvo = authService.cadastrar(usuario);
        salvo.setSenha("[PROTEGIDO]");
        return ResponseEntity.ok(salvo);
    }

}

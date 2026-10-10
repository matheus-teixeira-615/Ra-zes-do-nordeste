package raizesdonordeste.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raizesdonordeste.com.model.Role;
import raizesdonordeste.com.model.Usuario;
import raizesdonordeste.com.repository.UsuarioRepository;

import java.net.PasswordAuthentication;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario novoUsuario){
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));

        if(novoUsuario.getRoles() == null || novoUsuario.getRoles().isEmpty()){
            Set<Role> rolesPadrao = new HashSet<>();
            rolesPadrao.add(Role.CLIENTE);
            novoUsuario.setRoles(rolesPadrao);
        }
        return usuarioRepository.save(novoUsuario);
    }

    public void validarPermissao(String emailUsuario, Role roleExigida){
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario) . orElseThrow(() -> new RuntimeException("Usuario naõ encontrado"));

        if(!usuario.getRoles().contains(roleExigida)){
            throw new RuntimeException("Acesso negado.");
        }
    }

}

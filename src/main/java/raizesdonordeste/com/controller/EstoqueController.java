package raizesdonordeste.com.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizesdonordeste.com.model.Estoque;
import raizesdonordeste.com.repository.EstoqueRepository;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    private final EstoqueRepository estoqueRepository;

    public EstoqueController(EstoqueRepository estoqueRepository){
        this.estoqueRepository = estoqueRepository;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoque(){
        List<Estoque> listaEstoque = estoqueRepository.findAll();
        return ResponseEntity.ok(listaEstoque);
    }

    @PostMapping
    public ResponseEntity<Estoque> salvarEstoque(@RequestBody Estoque estoque){
        Estoque novoEstoque = estoqueRepository.save(estoque);
        return ResponseEntity.ok(novoEstoque);
    }
}

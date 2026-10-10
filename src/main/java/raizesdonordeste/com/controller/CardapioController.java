package raizesdonordeste.com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizesdonordeste.com.model.Produto;
import raizesdonordeste.com.model.Unidade;
import raizesdonordeste.com.repository.ProdutoRepository;
import raizesdonordeste.com.repository.UnidadeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    public CardapioController(UnidadeRepository unidadeRepository, ProdutoRepository produtoRepository){
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/unidades")
    public ResponseEntity<Unidade> criarUnidade(@RequestBody Unidade unidade){
        return ResponseEntity.ok(unidadeRepository.save(unidade));
    }

    @GetMapping("/unidades")
    public ResponseEntity<List<Unidade>> listarUnidades(){
        return ResponseEntity.ok(unidadeRepository.findAll());
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        if(produto.getUnidade() != null){
            Unidade unidadeCompleta = unidadeRepository.findById(produto.getUnidade().getId())
                    .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
            produto.setUnidade(unidadeCompleta);
        }
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<Produto>> consultarCardapioPorUnidade(@PathVariable Long unidadeId){
        List<Produto> cardapio = produtoRepository.findByUnidadeId(unidadeId);
        return ResponseEntity.ok(cardapio);
    }
}

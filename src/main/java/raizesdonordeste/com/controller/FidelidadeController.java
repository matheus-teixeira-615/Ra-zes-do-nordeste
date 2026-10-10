package raizesdonordeste.com.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizesdonordeste.com.model.Fidelidade;
import raizesdonordeste.com.repository.FidelidadeRepository;
import raizesdonordeste.com.service.FidelidadeService;

@RestController
@RequestMapping("/api/fidelidade")
public class FidelidadeController {

    private final FidelidadeRepository fidelidadeRepository;
    private final FidelidadeService fidelidadeService;

    public FidelidadeController(FidelidadeRepository fidelidadeRepository,FidelidadeService fidelidadeService){
        this.fidelidadeRepository = fidelidadeRepository;
        this.fidelidadeService = fidelidadeService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Fidelidade> consultarPontos (@PathVariable Long usuarioId) {
        Fidelidade fidelidade = fidelidadeRepository.findByUsuarioId(usuarioId) . orElseThrow(()-> new RuntimeException("Não encontradoo" + usuarioId));
        return ResponseEntity.ok(fidelidade);
    }

    @PostMapping("/resgatar")
    public ResponseEntity<Fidelidade> resgatarPontos(@RequestBody ResgateRequest request){
        Fidelidade fidelidadeAtualizada = fidelidadeService.resgatarPontos(
                request.getUsuarioId(),
                request.getPontosParaResgatar(),
                request.isConsentimento()
        );
        return ResponseEntity.ok(fidelidadeAtualizada);
    }

    @Data
    public static class ResgateRequest {
        private Long usuarioId;
        private int pontosParaResgatar;
        private boolean consentimento;

    }
}

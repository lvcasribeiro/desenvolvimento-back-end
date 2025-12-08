package projeto_garcom.com.demo.cozinha;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.pedido.PedidoMapper;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cozinha")
@RequiredArgsConstructor
public class CozinhaV1Controller {

    private final CozinhaService cozinhaService;
    private final PedidoMapper pedidoMapper;

    @GetMapping("/pendentes")
    public ResponseEntity<List<PedidoResponseDTO>> listarPendentes() {
        return ResponseEntity.ok(
                cozinhaService.listarPedidosPendentes().stream()
                        .map(pedidoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/em-preparo")
    public ResponseEntity<List<PedidoResponseDTO>> listarEmPreparo() {
        return ResponseEntity.ok(
                cozinhaService.listarPedidosEmPreparo().stream()
                        .map(pedidoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/prontos")
    public ResponseEntity<List<PedidoResponseDTO>> listarProntos() {
        return ResponseEntity.ok(
                cozinhaService.listarPedidosProntos().stream()
                        .map(pedidoMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{pedidoId}/iniciar")
    public ResponseEntity<PedidoResponseDTO> iniciarPreparo(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(cozinhaService.iniciarPreparo(pedidoId));
    }

    @PutMapping("/{pedidoId}/finalizar")
    public ResponseEntity<PedidoResponseDTO> finalizar(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(cozinhaService.finalizarPreparo(pedidoId));
    }
}

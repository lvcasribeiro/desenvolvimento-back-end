package projeto_garcom.com.demo.pedido;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.pedido.dto.PedidoRequestDTO;
import projeto_garcom.com.demo.pedido.dto.PedidoShowDTO;

@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoV1Controller {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(service.criarPedido(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(service.finalizarPedido(id));
    }
}
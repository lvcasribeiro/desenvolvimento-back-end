package projeto_garcom.com.demo.pedido;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.pedido.dto.PedidoRequestDTO;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;
import projeto_garcom.com.demo.pedido.dto.PedidoShowDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoV1Controller {

    private final PedidoService service;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(service.criarPedido(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<PedidoResponseDTO> iniciarPreparo(@PathVariable Long id) {
        return ResponseEntity.ok(service.iniciarPreparo(id));
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<PedidoResponseDTO> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(service.finalizarPedido(id));
    }

    @PostMapping("/{id}/entregar")
    public ResponseEntity<PedidoResponseDTO> entregar(@PathVariable Long id) {
        return ResponseEntity.ok(service.entregar(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar(
            @RequestParam(required = false) StatusPedido status
    ) {
        List<PedidoResponseDTO> pedidos = (status == null)
                ? service.listarTodos().stream()
                .map(pedidoMapper::toResponse)
                .toList()
                : service.listarPorStatus(status).stream()
                .map(pedidoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(pedidos);
    }

}

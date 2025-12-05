package projeto_garcom.com.demo.mesa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.mesa.dto.MesaRequestDTO;
import projeto_garcom.com.demo.mesa.dto.MesaShowDTO;
import projeto_garcom.com.demo.mesa.dto.MesaUpdateDTO;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaV1Controller {

    private final MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaShowDTO> criar(
            @Valid @RequestBody MesaRequestDTO dto) {

        MesaShowDTO mesa = mesaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaShowDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MesaUpdateDTO dto) {

        MesaShowDTO mesa = mesaService.atualizar(id, dto);
        return ResponseEntity.ok(mesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaShowDTO> buscarPorId(@PathVariable Long id) {
        MesaShowDTO mesa = mesaService.buscarPorId(id);
        return ResponseEntity.ok(mesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        mesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ocupar")
    public ResponseEntity<Void> ocuparMesa(@PathVariable Long id) {

        mesaService.ocuparMesa(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/liberar")
    public ResponseEntity<Void> liberarMesa(@PathVariable Long id) {

        mesaService.liberarMesa(id);
        return ResponseEntity.ok().build();
    }
}

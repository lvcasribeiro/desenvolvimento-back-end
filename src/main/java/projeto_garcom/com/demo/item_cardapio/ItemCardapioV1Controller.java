package projeto_garcom.com.demo.item_cardapio;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioRequestDTO;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/item-cardapio")
public class ItemCardapioV1Controller {

    private final ItemCardapioService service;

    public ItemCardapioV1Controller(ItemCardapioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponseDTO> criar(@RequestBody @Valid ItemCardapioRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ItemCardapioRequestDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        service.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        service.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ItemCardapioResponseDTO>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.listarPorCategoria(categoriaId));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ItemCardapioResponseDTO>> listarDisponiveis() {
        return ResponseEntity.ok(service.listarDisponiveis());
    }
}
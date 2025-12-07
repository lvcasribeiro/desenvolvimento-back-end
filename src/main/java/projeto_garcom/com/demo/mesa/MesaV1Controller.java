package projeto_garcom.com.demo.mesa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.common.dto.PaginatedResponse;
import projeto_garcom.com.demo.conta.ContaEntity;
import projeto_garcom.com.demo.conta.ContaMapper;
import projeto_garcom.com.demo.conta.dto.ContaShowDTO;
import projeto_garcom.com.demo.mesa.dto.MesaDTO;
import projeto_garcom.com.demo.mesa.dto.MesaRequestDTO;
import projeto_garcom.com.demo.mesa.dto.MesaShowDTO;
import projeto_garcom.com.demo.mesa.dto.MesaUpdateDTO;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaV1Controller {

    private final MesaService mesaService;
    private final ContaMapper contaMapper;
private final MesaMapper mesaMapper;

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

    @PostMapping("/{id}/iniciar-atendimento")
    public ResponseEntity<ContaShowDTO> iniciarAtendimento(@PathVariable Long id) {
        ContaEntity conta = mesaService.iniciarAtendimento(id);
        return ResponseEntity.ok(contaMapper.toShowDTO(conta));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<MesaShowDTO>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int perPage,
            @RequestParam(required = false) Boolean disponivel,
            @RequestParam(required = false) Long garcomId
    ) {
        Page<MesaShowDTO> mesaPage = mesaService.listar(page, perPage, disponivel, garcomId)
                .map(mesaMapper::toShowDTO);

        PaginatedResponse<MesaShowDTO> response = PaginatedResponse.<MesaShowDTO>builder()
                .data(mesaPage.getContent())
                .meta(PaginatedResponse.Meta.builder()
                        .totalItems(mesaPage.getTotalElements())
                        .totalPages(mesaPage.getTotalPages())
                        .currentPage(mesaPage.getNumber() + 1)
                        .perPage(mesaPage.getSize())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }
}

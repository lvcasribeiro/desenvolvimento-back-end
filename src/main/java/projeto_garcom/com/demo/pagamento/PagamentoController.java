package projeto_garcom.com.demo.pagamento;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.common.dto.PaginatedResponse;
import projeto_garcom.com.demo.pagamento.dto.PagamentoDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoRequestDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoShowDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoUpdateDTO;

@RestController
@RequestMapping("/api/v1/pagamento")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoMapper pagamentoMapper;

    @GetMapping
    public ResponseEntity<PaginatedResponse<PagamentoDTO>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int perPage,
            @RequestParam(required = false) String tipoPagamento
    ) {
        Page<PagamentoDTO> pagamentosPage = pagamentoService.listar(page, perPage, tipoPagamento)
                .map(pagamentoMapper::entityToPagamentoDTO);

        PaginatedResponse<PagamentoDTO> response = PaginatedResponse.<PagamentoDTO>builder()
                .data(pagamentosPage.getContent())
                .meta(PaginatedResponse.Meta.builder()
                        .totalItems(pagamentosPage.getTotalElements())
                        .totalPages(pagamentosPage.getTotalPages())
                        .currentPage(pagamentosPage.getNumber() + 1)
                        .perPage(pagamentosPage.getSize())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public PagamentoShowDTO buscarPorId(@PathVariable Long id) {
        return pagamentoMapper.entityToPagamentoShowDTO(pagamentoService.buscarPorId(id));
    }

    @PostMapping
    public PagamentoShowDTO criar(@RequestBody PagamentoRequestDTO dto) {
        return pagamentoMapper.entityToPagamentoShowDTO(pagamentoService.criar(dto));
    }

    @PutMapping("/{id}")
    public PagamentoShowDTO atualizar(@PathVariable Long id, @RequestBody PagamentoUpdateDTO dto) {
        return pagamentoMapper.entityToPagamentoShowDTO(pagamentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
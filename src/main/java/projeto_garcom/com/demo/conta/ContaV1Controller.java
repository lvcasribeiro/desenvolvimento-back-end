package projeto_garcom.com.demo.conta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.common.dto.PaginatedResponse;
import projeto_garcom.com.demo.conta.dto.ContaRequestDTO;
import projeto_garcom.com.demo.conta.dto.ContaShowDTO;
import projeto_garcom.com.demo.conta.dto.ContaUpdateDTO;
import projeto_garcom.com.demo.mesa.MesaService;
import projeto_garcom.com.demo.pagamento.dto.PagamentoRequestDTO;


@RestController
@RequestMapping("/api/v1/conta")
@RequiredArgsConstructor
public class ContaV1Controller {

    private final ContaService contaService;
    private final ContaMapper contaMapper;
    private final MesaService mesaService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<ContaShowDTO>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int perPage,
            @RequestParam(required = false) String nome
    ) {
        Page<ContaShowDTO> contasPage = contaService.buscarContas(page, perPage, nome)
                .map(contaMapper::toShowDTO);

        PaginatedResponse<ContaShowDTO> response = PaginatedResponse.<ContaShowDTO>builder()
                .data(contasPage.getContent())
                .meta(PaginatedResponse.Meta.builder()
                        .totalItems(contasPage.getTotalElements())
                        .totalPages(contasPage.getTotalPages())
                        .currentPage(contasPage.getNumber() + 1)
                        .perPage(contasPage.getSize())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ContaShowDTO atualizar(@PathVariable Long id, @RequestBody ContaUpdateDTO dto) {
        return contaMapper.toShowDTO(contaService.atualizar(id, dto));
    }

    @GetMapping("/{id}")
    public ContaShowDTO buscar(@PathVariable Long id) {
        ContaEntity conta = contaService.buscarPorId(id);
        return contaMapper.toShowDTO(conta);
    }

    @PutMapping("/{contaId}/finalizar")
    public ResponseEntity<ContaShowDTO> finalizarConta(
            @PathVariable Long contaId,
            @RequestBody PagamentoRequestDTO pagamentoRequest
    ) {
        ContaShowDTO dto = contaService.finalizarConta(contaId, pagamentoRequest);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pagar")
    public ContaShowDTO pagar(
            @PathVariable Long id,
            @RequestBody PagamentoRequestDTO dto
    ) {
        return contaService.pagar(id, dto);
    }
}

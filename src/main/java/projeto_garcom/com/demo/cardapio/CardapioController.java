package projeto_garcom.com.demo.cardapio;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import projeto_garcom.com.demo.cardapio.dto.CardapioDTO;
import projeto_garcom.com.demo.common.dto.PaginatedResponse;

@RestController
@RequestMapping("/cardapios")
@RequiredArgsConstructor
public class CardapioController {

    private final CardapioService cardapioService;
    private final CardapioMapper cardapioMapper;

    @GetMapping
    public ResponseEntity<PaginatedResponse<CardapioDTO>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int perPage,
            @RequestParam(required = false) Boolean disponivelNaCozinha
    ) {

        Page<CardapioDTO> cardapioPage = cardapioService.listar(page, perPage, disponivelNaCozinha)
                .map(cardapioMapper::toDTO);

        PaginatedResponse<CardapioDTO> response = PaginatedResponse.<CardapioDTO>builder()
                .data(cardapioPage.getContent())
                .meta(PaginatedResponse.Meta.builder()
                        .totalItems(cardapioPage.getTotalElements())
                        .totalPages(cardapioPage.getTotalPages())
                        .currentPage(cardapioPage.getNumber() + 1)
                        .perPage(cardapioPage.getSize())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }
}

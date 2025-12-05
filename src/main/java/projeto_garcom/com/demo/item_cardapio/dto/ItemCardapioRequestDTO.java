package projeto_garcom.com.demo.item_cardapio.dto;

import java.math.BigDecimal;

public record ItemCardapioRequestDTO(
        String nome,
        String ingredientes,
        BigDecimal preco,
        Boolean disponivelNaCozinha,
        Long categoriaId,
        Long cardapioId
) {}

package projeto_garcom.com.demo.item_cardapio.dto;

import java.math.BigDecimal;

public record ItemCardapioUpdateDTO(
        Long id,
        String nome,
        String ingredientes,
        BigDecimal preco,
        Boolean disponivelNaCozinha
) {}
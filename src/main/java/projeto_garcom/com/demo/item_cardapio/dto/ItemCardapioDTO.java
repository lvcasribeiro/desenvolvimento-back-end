package projeto_garcom.com.demo.item_cardapio.dto;

import java.math.BigDecimal;

public record ItemCardapioDTO(
        String nome,
        String ingredientes,
        BigDecimal preco,
        Boolean disponivelNaCozinha
) {}
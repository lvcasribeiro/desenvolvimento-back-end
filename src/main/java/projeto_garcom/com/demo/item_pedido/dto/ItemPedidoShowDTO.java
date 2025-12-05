package projeto_garcom.com.demo.item_pedido.dto;

import java.math.BigDecimal;

public record ItemPedidoShowDTO(
        Long id,
        Integer quantidade,
        Long itemCardapioId,
        String nomeItem,
        BigDecimal preco
) {}
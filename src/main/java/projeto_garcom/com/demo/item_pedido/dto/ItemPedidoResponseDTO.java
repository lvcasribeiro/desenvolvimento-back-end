package projeto_garcom.com.demo.item_pedido.dto;

public record ItemPedidoResponseDTO(
        Long id,
        String nomeItem,
        Integer quantidade,
        Double precoUnitario,
        Double subtotal
) { }
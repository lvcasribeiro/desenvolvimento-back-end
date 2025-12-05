package projeto_garcom.com.demo.pedido.dto;

import projeto_garcom.com.demo.item_pedido.dto.ItemPedidoRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequestDTO(
        Integer numero,
        Long clienteId,
        Long contaId,
        List<ItemPedidoRequestDTO> itens
) {}
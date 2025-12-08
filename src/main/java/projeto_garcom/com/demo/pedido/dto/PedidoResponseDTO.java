package projeto_garcom.com.demo.pedido.dto;

import projeto_garcom.com.demo.item_pedido.dto.ItemPedidoResponseDTO;
import projeto_garcom.com.demo.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        Integer numero,
        LocalDateTime horarioPedido,
        LocalDateTime horarioEntrega,
        Long clienteId,
        Long contaId,
        List<ItemPedidoResponseDTO> itens,
        StatusPedido status
) {}
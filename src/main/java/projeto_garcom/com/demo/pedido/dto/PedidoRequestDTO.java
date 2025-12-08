package projeto_garcom.com.demo.pedido.dto;

import projeto_garcom.com.demo.item_pedido.dto.ItemPedidoRequestDTO;
import projeto_garcom.com.demo.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequestDTO(
        Integer numero,
        String clienteNome,
        Long contaId,
        List<ItemPedidoRequestDTO> itens,
        StatusPedido statusPedido,
         Long mesaId
        ) {}
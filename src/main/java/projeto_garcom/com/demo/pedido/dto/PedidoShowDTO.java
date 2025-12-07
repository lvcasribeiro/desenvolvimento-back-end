package projeto_garcom.com.demo.pedido.dto;

import projeto_garcom.com.demo.pedido.StatusPedido;

import java.time.LocalDateTime;

public record PedidoShowDTO(
        Long id,
        Integer numero,
        StatusPedido statusPedido,
        LocalDateTime horarioPedido,
        LocalDateTime horarioEntrega
) {}
package projeto_garcom.com.demo.pedido.dto;

import java.time.LocalDateTime;

public record PedidoUpdateDTO(
        Long id,
        Integer numero,
        LocalDateTime horarioPedido,
        LocalDateTime horarioEntrega
) {}
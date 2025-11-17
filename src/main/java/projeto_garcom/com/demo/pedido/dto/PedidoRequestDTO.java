package projeto_garcom.com.demo.pedido.dto;

import java.time.LocalDateTime;

public record PedidoRequestDTO(
        Integer numero,
        LocalDateTime horarioPedido,
        LocalDateTime horarioEntrega
) {}
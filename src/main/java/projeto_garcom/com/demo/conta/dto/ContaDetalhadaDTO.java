package projeto_garcom.com.demo.conta.dto;

import projeto_garcom.com.demo.cliente.dto.ClienteDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoShowDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContaDetalhadaDTO(
        Long id,
        String nome,
        Long mesaId,
        Long caixaId,
        String caixaNome,
        Boolean aberta,
        BigDecimal totalConta,
        BigDecimal totalPago,
        BigDecimal saldoRestante,
        List<PagamentoShowDTO> pagamentos,
        List<PedidoConsumidoDTO> pedidos,
         ClienteDTO cliente
) {
    public record PedidoConsumidoDTO(
            Long id,
            Integer numero,
            LocalDateTime horarioPedido,
            LocalDateTime horarioEntrega,
            String status,
            List<ItemConsumidoDTO> itens
    ) {}

    public record ItemConsumidoDTO(
            Long id,
            String nomeItem,
            Integer quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal
    ) {}
}


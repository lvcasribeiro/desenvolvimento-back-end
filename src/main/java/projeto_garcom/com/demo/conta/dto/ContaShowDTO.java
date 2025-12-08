package projeto_garcom.com.demo.conta.dto;

import projeto_garcom.com.demo.pagamento.dto.PagamentoShowDTO;

import java.math.BigDecimal;
import java.util.List;

public record ContaShowDTO(
        Long id,
        String nome,
        Long mesaId,
        Long caixaId,
        String caixaNome,
        BigDecimal totalConta,
        BigDecimal totalPago,
        BigDecimal saldoRestante,
        //String status,
        List<PagamentoShowDTO> pagamentos
) {}
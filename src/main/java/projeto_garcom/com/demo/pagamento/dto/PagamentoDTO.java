package projeto_garcom.com.demo.pagamento.dto;

import projeto_garcom.com.demo.pagamento.PagamentoEnum;

public record PagamentoDTO(
        Integer nroTransacao,
        Integer numero,
        PagamentoEnum tipoPagamento
) {}
package projeto_garcom.com.demo.pagamento.dto;

import projeto_garcom.com.demo.pagamento.PagamentoEnum;

public record PagamentoUpdateDTO(
        Long id,
        PagamentoEnum pagamento
) {}
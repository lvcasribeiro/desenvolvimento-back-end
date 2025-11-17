package projeto_garcom.com.demo.pagamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import projeto_garcom.com.demo.pagamento.PagamentoEnum;

public record PagamentoUpdateDTO(
        @NotNull(message = "O id do registro deve ser informado.")
        Long id,
        @NotBlank(message = "O tipo de pagamento deve ser informado.")
        PagamentoEnum pagamento
) {}
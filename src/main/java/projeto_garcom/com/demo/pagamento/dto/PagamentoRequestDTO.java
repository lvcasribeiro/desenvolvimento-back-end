package projeto_garcom.com.demo.pagamento.dto;

import jakarta.validation.constraints.NotBlank;
import projeto_garcom.com.demo.pagamento.PagamentoEnum;

public record PagamentoRequestDTO(
        @NotBlank(message = "O tipo de pagamento deve ser informado.")
        PagamentoEnum pagamento
) {}
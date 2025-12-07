package projeto_garcom.com.demo.pagamento.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;
import projeto_garcom.com.demo.pagamento.PagamentoEnum;

public record PagamentoRequestDTO(
        @DefaultValue(value = "")
        Integer nroTransacao,

        @DefaultValue(value = "")
        Integer numero,

        @NotBlank(message = "O tipo de pagamento deve ser informado.")
        PagamentoEnum tipoPagamento
) {}
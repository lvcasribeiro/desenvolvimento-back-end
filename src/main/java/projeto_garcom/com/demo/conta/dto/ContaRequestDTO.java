package projeto_garcom.com.demo.conta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ContaRequestDTO(
        @NotBlank String nome,
        @NotNull Long mesaId,
        List<Long> pedidosIds
) {}

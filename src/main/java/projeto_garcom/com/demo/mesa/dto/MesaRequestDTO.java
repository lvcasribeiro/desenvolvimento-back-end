package projeto_garcom.com.demo.mesa.dto;

import jakarta.validation.constraints.NotNull;

public record MesaRequestDTO(
        @NotNull Integer numero,
        @NotNull Boolean disponivel,
        Long garcomId
) {}
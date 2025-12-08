package projeto_garcom.com.demo.mesa.dto;

import jakarta.validation.constraints.NotNull;

public record MesaOcuparDTO(
        @NotNull Long mesaId,
        @NotNull Long garcomId
) {}
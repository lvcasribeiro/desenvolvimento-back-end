package projeto_garcom.com.demo.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDTO(
        @NotNull(message = "O id do registro deve ser informado.")
        Long id,
        String nome,
        String login,
        String senha
) {}
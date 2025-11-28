package projeto_garcom.com.demo.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo 'nome' não pode estar vazio.")
        String nome,

        @NotBlank(message = "O campo 'login' não pode estar vazio.")
        String login,

        @NotBlank(message = "O campo 'senha' não pode estar vazio.")
        String senha
) {}
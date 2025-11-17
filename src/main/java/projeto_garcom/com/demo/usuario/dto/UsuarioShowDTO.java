package projeto_garcom.com.demo.usuario.dto;

public record UsuarioShowDTO(
        Long id,
        String nome,
        String login,
        String senha
) {}
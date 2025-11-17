package projeto_garcom.com.demo.usuario.dto;

public record UsuarioUpdateDTO(
        Long id,
        String nome,
        String login,
        String senha
) {}
package projeto_garcom.com.demo.usuario.dto;

import projeto_garcom.com.demo.usuario.TipoUsuarioEnum;

public record UsuarioShowDTO(
        Long id,
        String nome,
        String login,
        String senha,
        TipoUsuarioEnum tipo
) {}
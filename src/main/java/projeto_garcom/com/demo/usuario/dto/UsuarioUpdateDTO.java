package projeto_garcom.com.demo.usuario.dto;

import projeto_garcom.com.demo.usuario.TipoUsuarioEnum;

public record UsuarioUpdateDTO(
        String nome,
        String login,
        String senha,
        TipoUsuarioEnum tipo
) {}
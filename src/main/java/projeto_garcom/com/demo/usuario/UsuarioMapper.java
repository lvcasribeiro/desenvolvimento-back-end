package projeto_garcom.com.demo.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.usuario.dto.UsuarioDTO;
import projeto_garcom.com.demo.usuario.dto.UsuarioRequestDTO;
import projeto_garcom.com.demo.usuario.dto.UsuarioShowDTO;
import projeto_garcom.com.demo.usuario.dto.UsuarioUpdateDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioEntity toEntityFromRequest(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioDTO entityToUsuarioDTO(UsuarioEntity usuario);
    UsuarioShowDTO entityToUsuarioShowDTO(UsuarioEntity usuario);

    void updateUsuarioFromUsuarioUpdateDTO(UsuarioUpdateDTO usuarioUpdateDTO, @MappingTarget UsuarioEntity usuario);
}
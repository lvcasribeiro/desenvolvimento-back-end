package projeto_garcom.com.demo.usuario;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_garcom.com.demo.common.dto.PaginatedResponse;
import projeto_garcom.com.demo.usuario.dto.UsuarioDTO;
import projeto_garcom.com.demo.usuario.dto.UsuarioRequestDTO;
import projeto_garcom.com.demo.usuario.dto.UsuarioShowDTO;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioV1Controller {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioV1Controller(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<UsuarioDTO>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int perPage,
            @RequestParam(required = false) String nome
    ) {
        Page<UsuarioDTO> usuariosPage = usuarioService.buscarUsuarios(page, perPage, nome)
                .map(usuarioMapper::entityToUsuarioDTO);

        PaginatedResponse<UsuarioDTO> response = PaginatedResponse.<UsuarioDTO>builder()
                .data(usuariosPage.getContent())
                .meta(PaginatedResponse.Meta.builder()
                        .totalItems(usuariosPage.getTotalElements())
                        .totalPages(usuariosPage.getTotalPages())
                        .currentPage(usuariosPage.getNumber() + 1)
                        .perPage(usuariosPage.getSize())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public UsuarioShowDTO buscarPorId(@PathVariable Long id) {
        return usuarioMapper.entityToUsuarioShowDTO(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public UsuarioShowDTO criar(@RequestBody UsuarioRequestDTO dto) {
        return usuarioMapper.entityToUsuarioShowDTO(usuarioService.criar(dto));
    }

    @PutMapping("/{id}")
    public UsuarioShowDTO atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {

        return usuarioMapper.entityToUsuarioShowDTO(usuarioService.atualizar(id, dto));
    }
}
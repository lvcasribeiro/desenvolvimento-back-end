package projeto_garcom.com.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.usuario.dto.UsuarioRequestDTO;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public Page<UsuarioEntity> buscarUsuarios(int page, int perPage, String nome) {
        int pageNumber = Math.max(page - 1, 0);
        int pageSize = perPage > 0 ? perPage : 20;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<UsuarioEntity> spec = UsuarioSpecification.byNome(nome);

        return usuarioRepository.findAll(spec, pageable);
    }

    public UsuarioEntity buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public UsuarioEntity criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new InvalidEntityException("Já existe um usuário com esse login.");
        }

        UsuarioEntity entity = usuarioMapper.toEntityFromRequest(dto);
        return usuarioRepository.save(entity);
    }

    @Transactional
    public UsuarioEntity atualizar(Long id, UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByLoginAndIdNot(dto.login(), id)) {
            throw new InvalidEntityException("Já existe um usuário com esse login.");
        }

        UsuarioEntity entity = buscarPorId(id);
        usuarioMapper.updateUsuarioFromUsuarioUpdateDTO(dto, entity);

        return usuarioRepository.save(entity);
    }
}
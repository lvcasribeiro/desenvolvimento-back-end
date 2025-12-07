package projeto_garcom.com.demo.mesa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.conta.ContaEntity;
import projeto_garcom.com.demo.conta.ContaRepository;
import projeto_garcom.com.demo.mesa.dto.MesaRequestDTO;
import projeto_garcom.com.demo.mesa.dto.MesaShowDTO;
import projeto_garcom.com.demo.mesa.dto.MesaUpdateDTO;
import projeto_garcom.com.demo.usuario.TipoUsuarioEnum;
import projeto_garcom.com.demo.usuario.UsuarioEntity;
import projeto_garcom.com.demo.usuario.UsuarioRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MesaMapper mesaMapper;
    private final ContaRepository contaRepository;

    @Transactional
    public MesaShowDTO criar(MesaRequestDTO dto) {

        MesaEntity entity = mesaMapper.toEntity(dto);

        if (dto.garcomId() != null) {
            UsuarioEntity garcom = buscarGarcom(dto.garcomId());
            entity.setGarcom(garcom);
        } else {
            entity.setGarcom(null);
        }

        MesaEntity saved = mesaRepository.save(entity);
        return mesaMapper.toShowDTO(saved);
    }

    @Transactional
    public MesaShowDTO atualizar(Long id, MesaUpdateDTO dto) {
        MesaEntity mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada: " + id));

        mesaMapper.updateEntity(dto, mesa);

        if (dto.garcomId() != null) {
            UsuarioEntity garcom = buscarGarcom(dto.garcomId());
            mesa.setGarcom(garcom);
        }

        MesaEntity saved = mesaRepository.save(mesa);
        return mesaMapper.toShowDTO(saved);
    }

    public MesaShowDTO buscarPorId(Long id) {
        MesaEntity mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada: " + id));

        return mesaMapper.toShowDTO(mesa);
    }

    @Transactional
    public void deletar(Long id) {
        if (!mesaRepository.existsById(id)) {
            throw new NotFoundException("Mesa não encontrada: " + id);
        }
        mesaRepository.deleteById(id);
    }

    @Transactional
    public void ocuparMesa(Long mesaId) {
        MesaEntity mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada: " + mesaId));

        if (!mesa.getDisponivel()) {
            throw new InvalidEntityException("Mesa já está ocupada");
        }

        mesa.setDisponivel(false);
        mesaRepository.save(mesa);
    }

    @Transactional
    public void liberarMesa(Long mesaId) {
        MesaEntity mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada: " + mesaId));

        mesa.setDisponivel(true);
        mesaRepository.save(mesa);
    }

    private UsuarioEntity buscarGarcom(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado: " + id));

        if (usuario.getTipo() != TipoUsuarioEnum.GARCOM) {
            throw new InvalidEntityException("Usuário informado não é um garçom.");
        }

        return usuario;
    }

    @Transactional
    public ContaEntity iniciarAtendimento(Long mesaId) {

        MesaEntity mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada: " + mesaId));

        if (!mesa.getDisponivel()) {
            throw new InvalidEntityException("Mesa já está ocupada");
        }

        mesa.setDisponivel(false);

        ContaEntity conta = new ContaEntity();
        conta.setNome("Conta Mesa " + mesa.getNumero());
        conta.setAberta(true);
        conta.setMesa(mesa);

        conta.setCaixa(null);

        contaRepository.save(conta);
        mesaRepository.save(mesa);

        return conta;
    }

    public Page<MesaEntity> listar(int page, int perPage, Boolean disponivel, Long garcomId) {
        int pageNumber = Math.max(page - 1, 0);
        int pageSize = perPage > 0 ? perPage : 20;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<MesaEntity> spec = MesaSpecification.disponivel(disponivel).and(MesaSpecification.garcom(garcomId));

        return mesaRepository.findAll(spec, pageable);
    }
}

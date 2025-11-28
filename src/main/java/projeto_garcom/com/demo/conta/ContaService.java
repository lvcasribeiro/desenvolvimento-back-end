package projeto_garcom.com.demo.conta;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.conta.dto.ContaRequestDTO;
import projeto_garcom.com.demo.conta.dto.ContaUpdateDTO;
import projeto_garcom.com.demo.mesa.MesaEntity;
import projeto_garcom.com.demo.mesa.MesaRepository;
import projeto_garcom.com.demo.mesa.MesaService;
import projeto_garcom.com.demo.pagamento.PagamentoEntity;
import projeto_garcom.com.demo.pagamento.PagamentoRepository;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.pedido.PedidoRepository;
import projeto_garcom.com.demo.usuario.TipoUsuarioEnum;
import projeto_garcom.com.demo.usuario.UsuarioEntity;
import projeto_garcom.com.demo.usuario.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final MesaRepository mesaRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ContaMapper contaMapper;
    private final UsuarioRepository usuarioRepository;
    private final PedidoRepository pedidoRepository;
    private final MesaService mesaService;

    public Page<ContaEntity> buscarContas(int page, int perPage, String nome) {
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), perPage);
        Specification<ContaEntity> spec = ContaSpecification.byNome(nome);
        return contaRepository.findAll(spec, pageable);
    }

    public ContaEntity buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));
    }

    @Transactional
    public ContaEntity criar(ContaRequestDTO dto) {

        ContaEntity conta = contaMapper.toEntity(dto);

        UsuarioEntity caixa = usuarioRepository.findById(dto.caixaId())
                .orElseThrow(() -> new NotFoundException("Caixa não encontrado."));

        if (caixa.getTipo() != TipoUsuarioEnum.CAIXA) {
            throw new InvalidEntityException("Usuário informado não é do tipo CAIXA.");
        }

        conta.setCaixa(caixa);

        MesaEntity mesa = mesaRepository.findById(dto.mesaId())
                .orElseThrow(() -> new NotFoundException("Mesa não encontrada."));
        conta.setMesa(mesa);

        mesaService.ocuparMesa(mesa.getId());

        if (dto.pedidosIds() != null) {
            List<PedidoEntity> pedidos = pedidoRepository.findAllById(dto.pedidosIds());
            conta.setPedidos(pedidos);
        }

        // Todo: no momento da criação da conta ja devemos passar o pagamento? creio que não, discutir com o manos
//        if (dto.pagamentoId() != null) {
//            PagamentoEntity pagamento = pagamentoRepository.findById(dto.pagamentoId())
//                    .orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));
//            conta.setPagamento(pagamento);
//        }

        conta.setAberta(true);

        return contaRepository.save(conta);
    }

    @Transactional
    public ContaEntity atualizar(Long id, ContaUpdateDTO dto) {

        ContaEntity conta = contaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        MesaEntity mesaOriginal = conta.getMesa();

        contaMapper.updateEntity(dto, conta);

        if (dto.caixaId() != null) {
            UsuarioEntity caixa = usuarioRepository.findById(dto.caixaId())
                    .orElseThrow(() -> new NotFoundException("Caixa não encontrado."));

            if (caixa.getTipo() != TipoUsuarioEnum.CAIXA) {
                throw new InvalidEntityException("Usuário informado não é do tipo CAIXA.");
            }

            conta.setCaixa(caixa);
        }

        if (dto.mesaId() != null && !dto.mesaId().equals(mesaOriginal.getId())) {

            mesaService.liberarMesa(mesaOriginal.getId());

            MesaEntity novaMesa = mesaRepository.findById(dto.mesaId())
                    .orElseThrow(() -> new NotFoundException("Nova mesa não encontrada."));

            mesaService.ocuparMesa(novaMesa.getId());
            conta.setMesa(novaMesa);
        }

        return contaRepository.save(conta);
    }

    @Transactional
    public void fecharConta(Long id) {
        ContaEntity conta = contaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        mesaService.liberarMesa(conta.getMesa().getId());

        conta.setAberta(false);
        contaRepository.save(conta);
    }

}
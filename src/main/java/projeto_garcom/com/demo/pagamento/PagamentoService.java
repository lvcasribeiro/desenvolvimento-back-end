package projeto_garcom.com.demo.pagamento;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.pagamento.dto.PagamentoRequestDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoUpdateDTO;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;

    public Page<PagamentoEntity> listar(int page, int perPage, String tipoPagamento) {
        int pageNumber = Math.max(page - 1, 0);
        int pageSize = perPage > 0 ? perPage : 20;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<PagamentoEntity> spec = PagamentoSpecification.byTipoPagamento(tipoPagamento);

        return pagamentoRepository.findAll(spec, pageable);
    }

    public PagamentoEntity buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));
    }

    @Transactional
    public PagamentoEntity criar(PagamentoRequestDTO dto) {
        if (dto.tipoPagamento() == PagamentoEnum.CARTAO && dto.nroTransacao() == null) {
            throw new InvalidEntityException("Para o método de pagamento em cartão, o número da transação é obrigatório.");
        } else if (dto.tipoPagamento() == PagamentoEnum.CHEQUE && dto.numero() == null) {
            throw new InvalidEntityException("Para o método de pagamento em cheque, o número do cheque é obrigatório.");
        }

        PagamentoEntity entity = pagamentoMapper.toEntityFromRequest(dto);

        if (dto.tipoPagamento() == PagamentoEnum.CARTAO) {
            entity.setNumero(null);
        } else if (dto.tipoPagamento() == PagamentoEnum.CHEQUE) {
            entity.setNroTransacao(null);
        }

        return pagamentoRepository.save(entity);
    }

    @Transactional
    public PagamentoEntity atualizar(Long id, PagamentoUpdateDTO dto) {
        if (dto.tipoPagamento() == PagamentoEnum.CARTAO && dto.nroTransacao() == null) {
            throw new InvalidEntityException("Para o método de pagamento em cartão, o número da transação é obrigatório.");
        } else if (dto.tipoPagamento() == PagamentoEnum.CHEQUE && dto.numero() == null) {
            throw new InvalidEntityException("Para o método de pagamento em cheque, o número do cheque é obrigatório.");
        }

        PagamentoEntity entity = buscarPorId(id);

        if (dto.tipoPagamento() == PagamentoEnum.CARTAO) {
            entity.setNumero(null);
        } else if (dto.tipoPagamento() == PagamentoEnum.CHEQUE) {
            entity.setNroTransacao(null);
        }

        pagamentoMapper.updatePagamentoFromUpdateDTO(dto, entity);

        return pagamentoRepository.save(entity);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new NotFoundException("Pagamento não encontrado.");
        }

        pagamentoRepository.deleteById(id);
    }
}
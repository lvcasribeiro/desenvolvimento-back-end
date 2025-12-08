package projeto_garcom.com.demo.conta;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.cliente.ClienteEntity;
import projeto_garcom.com.demo.cliente.dto.ClienteDTO;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.conta.dto.*;
import projeto_garcom.com.demo.mesa.MesaEntity;
import projeto_garcom.com.demo.mesa.MesaMapper;
import projeto_garcom.com.demo.mesa.MesaRepository;
import projeto_garcom.com.demo.mesa.MesaService;
import projeto_garcom.com.demo.mesa.dto.MesaOcuparDTO;
import projeto_garcom.com.demo.pagamento.PagamentoEntity;
import projeto_garcom.com.demo.pagamento.PagamentoMapper;
import projeto_garcom.com.demo.pagamento.PagamentoRepository;
import projeto_garcom.com.demo.pagamento.PagamentoService;
import projeto_garcom.com.demo.pagamento.dto.PagamentoRequestDTO;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.pedido.PedidoRepository;
import projeto_garcom.com.demo.pedido.StatusPedido;
import projeto_garcom.com.demo.usuario.TipoUsuarioEnum;
import projeto_garcom.com.demo.usuario.UsuarioEntity;
import projeto_garcom.com.demo.usuario.UsuarioRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
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
    private final PagamentoService pagamentoService;
    private final PagamentoMapper pagamentoMapper;
    private final MesaMapper mesaMapper;

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

            MesaOcuparDTO mesaOcuparDTO = mesaMapper.toMesaOcuparDTO(novaMesa);

            mesaService.ocuparMesa(mesaOcuparDTO);
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

    public BigDecimal calcularTotalDaConta(ContaEntity conta) {
        if (conta.getPedidos() == null) {
            return BigDecimal.ZERO;
        }

        return conta.getPedidos().stream()
                .flatMap(p -> p.getItensPedido().stream())
                .map(item -> item.getItemCardapio().getPreco()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public BigDecimal recalcularTotalESalvar(Long contaId) {
        ContaEntity conta = buscarPorId(contaId);

        BigDecimal total = conta.getPedidos() == null
                ? BigDecimal.ZERO
                : conta.getPedidos().stream()
                .flatMap(p -> p.getItensPedido().stream())
                .map(item -> item.getItemCardapio().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        conta.setTotal(total);
        contaRepository.save(conta);

        return total;
    }

    @Transactional
    public ContaDetalhadaDTO finalizarConta(ContaFinalizarDTO dto) {

        ContaEntity conta = contaRepository.findById(dto.contaId())
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        if (!conta.getAberta()) {
            throw new InvalidEntityException("Conta já está fechada.");
        }

        UsuarioEntity caixa = usuarioRepository.findById(dto.caixaId())
                .orElseThrow(() -> new NotFoundException("Usuário (caixa) não encontrado."));
        conta.setCaixa(caixa);

        List<PedidoEntity> pedidosEntregues =
                pedidoRepository.findAllByContaIdAndStatus(dto.contaId(), StatusPedido.ENTREGUE);

        if (pedidosEntregues.isEmpty()) {
            throw new InvalidEntityException("Não há pedidos entregues para finalizar a conta.");
        }

        // CALCULAR TOTAL
        BigDecimal totalCalculado = pedidosEntregues.stream()
                .flatMap(p -> p.getItensPedido().stream())
                .map(item -> item.getItemCardapio().getPreco()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        conta.setTotal(totalCalculado);

        // VALIDAR SALDO
        BigDecimal saldoRestante = conta.getSaldoRestante();

        if (saldoRestante.compareTo(BigDecimal.ZERO) > 0) {
            throw new InvalidEntityException(
                    "Conta possui pendências de pagamento. Restam: " + saldoRestante
            );
        }

        // FECHAR A CONTA
        conta.setAberta(false);
        conta.getMesa().setDisponivel(true);

        ClienteEntity cliente = pedidosEntregues.get(0).getCliente();
        if (cliente != null) {
            cliente.setHoraSaida(LocalDateTime.now());
        }

        conta = contaRepository.save(conta);

        // DTO DE PEDIDOS
        List<ContaDetalhadaDTO.PedidoConsumidoDTO> pedidosDTO = pedidosEntregues.stream()
                .map(p -> new ContaDetalhadaDTO.PedidoConsumidoDTO(
                        p.getId(),
                        p.getNumero(),
                        p.getHorarioPedido(),
                        p.getHorarioEntrega(),
                        p.getStatus().name(),
                        p.getItensPedido().stream()
                                .map(i -> new ContaDetalhadaDTO.ItemConsumidoDTO(
                                        i.getId(),
                                        i.getItemCardapio().getNome(),
                                        i.getQuantidade(),
                                        i.getItemCardapio().getPreco(),
                                        i.getItemCardapio().getPreco()
                                                .multiply(BigDecimal.valueOf(i.getQuantidade()))
                                ))
                                .toList()
                ))
                .toList();

        // NOVO → Dados do cliente
        ClienteDTO clienteDTO = cliente != null
                ? new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getHoraChegada(),
                cliente.getHoraSaida()
        )
                : null;

        return new ContaDetalhadaDTO(
                conta.getId(),
                conta.getNome(),
                conta.getMesa().getId(),
                caixa.getId(),
                caixa.getNome(),
                conta.getAberta(),
                conta.getTotalConta(),
                conta.getTotalPago(),
                conta.getSaldoRestante(),
                conta.getPagamentos().stream().map(pagamentoMapper::entityToPagamentoShowDTO).toList(),
                pedidosDTO,
                clienteDTO
        );
    }




    @Transactional
    public ContaShowDTO pagar(Long contaId, PagamentoRequestDTO dto) {

        ContaEntity conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada."));

        if (!conta.getAberta()) {
            throw new InvalidEntityException("Conta já está fechada.");
        }

        BigDecimal valorPagamento = dto.valor();

        if (valorPagamento == null || valorPagamento.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidEntityException("Valor do pagamento deve ser maior que zero.");
        }

        BigDecimal saldoAtual = conta.getSaldoRestante();

        if (valorPagamento.compareTo(saldoAtual) > 0) {
            throw new InvalidEntityException(
                    "Pagamento excede o valor devido. Excedente: " +
                            valorPagamento.subtract(saldoAtual)
            );
        }

        PagamentoEntity pagamento = PagamentoEntity.builder()
                .valor(valorPagamento)
                .tipoPagamento(dto.tipoPagamento())
                .numero(dto.numero())
                .nroTransacao(dto.nroTransacao())
                .conta(conta)
                .build();

        pagamentoRepository.save(pagamento);
        conta.getPagamentos().add(pagamento);

        contaRepository.save(conta);

        return contaMapper.toShowDTO(conta);
    }
}
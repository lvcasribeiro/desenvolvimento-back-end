package projeto_garcom.com.demo.pedido;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.cliente.ClienteEntity;
import projeto_garcom.com.demo.cliente.ClienteRepository;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.conta.ContaEntity;
import projeto_garcom.com.demo.conta.ContaRepository;
import projeto_garcom.com.demo.conta.ContaService;
import projeto_garcom.com.demo.cozinha.CozinhaEntity;
import projeto_garcom.com.demo.cozinha.CozinhaRepository;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioRepository;
import projeto_garcom.com.demo.item_pedido.ItemPedidoEntity;
import projeto_garcom.com.demo.item_pedido.ItemPedidoRepository;
import projeto_garcom.com.demo.pedido.dto.PedidoRequestDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final PedidoMapper pedidoMapper;
    private final CozinhaRepository cozinhaRepository;
    private final ContaService contaService;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {

        PedidoEntity pedido = new PedidoEntity();
        pedido.setNumero(dto.numero());
        pedido.setHorarioPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.RECEBIDO);

        ClienteEntity cliente = resolveCliente(dto);
        pedido.setCliente(cliente);

        if (dto.contaId() != null) {
            ContaEntity conta = contaRepository.findById(dto.contaId())
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
            pedido.setConta(conta);
        }

        CozinhaEntity cozinha = cozinhaRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Cozinha não encontrada"));
        pedido.setCozinha(cozinha);

        PedidoEntity pedidoSalvo = pedidoRepository.save(pedido);

        List<ItemPedidoEntity> itens = dto.itens().stream()
                .map(item -> {
                    ItemCardapioEntity cardapio = itemCardapioRepository.findById(item.itemCardapioId())
                            .orElseThrow(() -> new RuntimeException("Item inexistente"));

                    if (!cardapio.getDisponivelNaCozinha()) {
                        throw new RuntimeException("Item indisponível na cozinha");
                    }

                    ItemPedidoEntity itemPedido = new ItemPedidoEntity();
                    itemPedido.setPedido(pedidoSalvo);
                    itemPedido.setItemCardapio(cardapio);
                    itemPedido.setQuantidade(item.quantidade());

                    return itemPedidoRepository.save(itemPedido);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        pedidoSalvo.setItensPedido(itens);

        if (pedidoSalvo.getConta() != null) {
            contaService.recalcularTotalESalvar(pedidoSalvo.getConta().getId());
        }

        return pedidoMapper.toResponse(pedidoSalvo);
    }


    @Transactional
    public PedidoResponseDTO iniciarPreparo(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.EM_PREPARO);
        return pedidoMapper.toResponse(pedidoRepository.save(pedido));
    }

    @Transactional
    public PedidoResponseDTO finalizarPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.PRONTO);
        pedido.setHorarioEntrega(LocalDateTime.now());

        PedidoEntity salvo = pedidoRepository.save(pedido);

        if (salvo.getConta() != null) {
            contaService.recalcularTotalESalvar(salvo.getConta().getId());
        }

        return pedidoMapper.toResponse(salvo);
    }

    @Transactional
    public PedidoResponseDTO entregar(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.ENTREGUE);

        PedidoEntity salvo = pedidoRepository.save(pedido);

        if (salvo.getConta() != null) {
            contaService.recalcularTotalESalvar(salvo.getConta().getId());
        }

        return pedidoMapper.toResponse(salvo);
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return pedidoMapper.toResponse(pedido);
    }

    public List<PedidoEntity> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<PedidoEntity> listarPorStatus(StatusPedido status) {
        if (status == null) {
            return listarTodos();
        }
        return pedidoRepository.findByStatus(status);
    }

    private ClienteEntity resolveCliente(PedidoRequestDTO dto) {

        if (dto.clienteNome() != null && !dto.clienteNome().isBlank()) {
            ClienteEntity novo = new ClienteEntity();
            novo.setNome(dto.clienteNome());
            novo.setHoraChegada(LocalDateTime.now());
            novo.setHoraSaida(null);
            return clienteRepository.save(novo);
        }

        throw new RuntimeException("É necessário informar clienteId ou clienteNome");
    }

}

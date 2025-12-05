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
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioRepository;
import projeto_garcom.com.demo.item_pedido.ItemPedidoEntity;
import projeto_garcom.com.demo.item_pedido.ItemPedidoRepository;
import projeto_garcom.com.demo.pedido.dto.PedidoRequestDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final ItemCardapioRepository itemCardapioRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {

        PedidoEntity pedido = new PedidoEntity();
        pedido.setNumero(dto.numero());
        pedido.setHorarioPedido(LocalDateTime.now());
        pedido.setHorarioEntrega(null);

        if (dto.clienteId() != null) {
            ClienteEntity cliente = clienteRepository.findById(dto.clienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            pedido.setCliente(cliente);
        }

        if (dto.contaId() != null) {
            ContaEntity conta = contaRepository.findById(dto.contaId())
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
            pedido.setConta(conta);
        }

        PedidoEntity pedidoSalvo = pedidoRepository.save(pedido);


        List<ItemPedidoEntity> itens = dto.itens().stream().map(item -> {
            ItemCardapioEntity cardapio = itemCardapioRepository.findById(item.itemCardapioId())
                    .orElseThrow(() -> new RuntimeException("Item do cardápio inexistente"));

            if (!cardapio.getDisponivelNaCozinha()) {
                throw new RuntimeException("Item indisponível na cozinha");
            }

            ItemPedidoEntity itemPedido = new ItemPedidoEntity();
            itemPedido.setPedido(pedidoSalvo);
            itemPedido.setItemCardapio(cardapio);
            itemPedido.setQuantidade(item.quantidade());

            return itemPedidoRepository.save(itemPedido);

        }).toList();

        pedidoSalvo.setItensPedido(itens);

        return PedidoMapper.toResponse(pedidoSalvo);
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return PedidoMapper.toResponse(pedido);
    }

    @Transactional
    public PedidoResponseDTO finalizarPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setHorarioEntrega(LocalDateTime.now());

        return PedidoMapper.toResponse(pedidoRepository.save(pedido));
    }
}
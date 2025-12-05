package projeto_garcom.com.demo.pedido;

import org.springframework.stereotype.Component;
import projeto_garcom.com.demo.cliente.ClienteEntity;
import projeto_garcom.com.demo.cliente.ClienteRepository;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.conta.ContaEntity;
import projeto_garcom.com.demo.conta.ContaRepository;
import projeto_garcom.com.demo.item_pedido.ItemPedidoEntity;
import projeto_garcom.com.demo.item_pedido.ItemPedidoMapper;
import projeto_garcom.com.demo.item_pedido.dto.ItemPedidoResponseDTO;
import projeto_garcom.com.demo.pedido.dto.PedidoRequestDTO;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public static PedidoResponseDTO toResponse(PedidoEntity pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getNumero(),
                pedido.getHorarioPedido(),
                pedido.getHorarioEntrega(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                pedido.getConta() != null ? pedido.getConta().getId() : null,
                pedido.getItensPedido().stream()
                        .map(PedidoMapper::toItemResponse)
                        .collect(Collectors.toList())
        );
    }

    private static ItemPedidoResponseDTO toItemResponse(ItemPedidoEntity item) {
        return new ItemPedidoResponseDTO(
                item.getId(),
                item.getItemCardapio().getNome(),
                item.getQuantidade(),
                item.getItemCardapio().getPreco().doubleValue(),
                item.getQuantidade() * item.getItemCardapio().getPreco().doubleValue()
        );
    }
}
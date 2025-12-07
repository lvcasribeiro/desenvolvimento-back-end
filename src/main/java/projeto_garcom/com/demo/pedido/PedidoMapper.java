package projeto_garcom.com.demo.pedido;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "conta.id", target = "contaId")
    @Mapping(source = "itensPedido", target = "itens")
    PedidoResponseDTO toResponse(PedidoEntity pedido);

    @Mapping(source = "itemCardapio.nome", target = "nomeItem")
    @Mapping(source = "itemCardapio.preco", target = "precoUnitario")
    @Mapping(target = "subtotal", expression = "java(calcularSubtotal(item))")
    ItemPedidoResponseDTO toItemResponse(ItemPedidoEntity item);

    default Double calcularSubtotal(ItemPedidoEntity item) {
        if (item == null || item.getItemCardapio() == null) return 0.0;

        return item.getQuantidade() * item.getItemCardapio().getPreco().doubleValue();
    }
}
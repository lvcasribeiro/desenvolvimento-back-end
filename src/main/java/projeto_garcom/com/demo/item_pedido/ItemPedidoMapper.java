package projeto_garcom.com.demo.item_pedido;

import org.springframework.stereotype.Component;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioRepository;
import projeto_garcom.com.demo.item_pedido.dto.ItemPedidoRequestDTO;
import projeto_garcom.com.demo.pedido.PedidoEntity;

@Component
public class ItemPedidoMapper {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemPedidoMapper(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemPedidoEntity toEntity(ItemPedidoRequestDTO dto, PedidoEntity pedido) {

        ItemCardapioEntity itemCardapio = itemCardapioRepository.findById(dto.itemCardapioId())
                .orElseThrow(() -> new NotFoundException("Item de cardápio não encontrado"));

        return ItemPedidoEntity.builder()
                .quantidade(dto.quantidade())
                .pedido(pedido)
                .itemCardapio(itemCardapio)
                .build();
    }
}

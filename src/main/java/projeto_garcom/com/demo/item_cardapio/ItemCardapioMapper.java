package projeto_garcom.com.demo.item_cardapio;

import org.springframework.stereotype.Component;
import projeto_garcom.com.demo.cardapio.CardapioEntity;
import projeto_garcom.com.demo.categoria.CategoriaEntity;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioRequestDTO;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

@Component
public class ItemCardapioMapper {

    public ItemCardapioEntity toEntity(
            ItemCardapioRequestDTO dto,
            CategoriaEntity categoria,
            CardapioEntity cardapio
    ) {
        return ItemCardapioEntity.builder()
                .nome(dto.nome())
                .ingredientes(dto.ingredientes())
                .preco(dto.preco())
                .disponivelNaCozinha(dto.disponivelNaCozinha())
                .categoria(categoria)
                .cardapio(cardapio)
                .build();
    }

    public void updateEntity(
            ItemCardapioEntity entity,
            ItemCardapioRequestDTO dto,
            CategoriaEntity categoria,
            CardapioEntity cardapio
    ) {
        entity.setNome(dto.nome());
        entity.setIngredientes(dto.ingredientes());
        entity.setPreco(dto.preco());
        entity.setDisponivelNaCozinha(dto.disponivelNaCozinha());
        entity.setCategoria(categoria);
        entity.setCardapio(cardapio);
    }

    public ItemCardapioResponseDTO toResponse(ItemCardapioEntity e) {
        return new ItemCardapioResponseDTO(
                e.getId(),
                e.getNome(),
                e.getIngredientes(),
                e.getPreco(),
                e.getDisponivelNaCozinha(),
                e.getCategoria().getId(),
                e.getCardapio().getId()
        );
    }
}

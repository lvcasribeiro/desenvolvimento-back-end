package projeto_garcom.com.demo.cardapio;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto_garcom.com.demo.cardapio.dto.CardapioDTO;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    @Mapping(target = "categoriaId", source = "categoria.id")
    ItemCardapioResponseDTO toItemDTO(ItemCardapioEntity entity);

    List<ItemCardapioResponseDTO> toItemDTOList(List<ItemCardapioEntity> list);

    @Mapping(target = "itens", source = "itens")
    CardapioDTO toDTO(CardapioEntity entity);
}
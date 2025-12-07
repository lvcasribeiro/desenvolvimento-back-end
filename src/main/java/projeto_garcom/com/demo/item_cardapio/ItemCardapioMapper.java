package projeto_garcom.com.demo.item_cardapio;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import projeto_garcom.com.demo.cardapio.CardapioEntity;
import projeto_garcom.com.demo.categoria.CategoriaEntity;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioRequestDTO;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

@Mapper(componentModel = "spring")
public interface ItemCardapioMapper {

    // CREATE
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "cardapio", ignore = true)
    ItemCardapioEntity toEntity(ItemCardapioRequestDTO dto);

    // UPDATE
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "cardapio", ignore = true)
    void updateEntity(@MappingTarget ItemCardapioEntity entity,
                      ItemCardapioRequestDTO dto);

    // RESPONSE
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "cardapio.id", target = "cardapioId")
    ItemCardapioResponseDTO toResponse(ItemCardapioEntity entity);
}

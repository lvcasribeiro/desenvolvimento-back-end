package projeto_garcom.com.demo.mesa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.mesa.dto.*;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garcom", ignore = true)
    MesaEntity toEntity(MesaRequestDTO dto);

    @Mapping(target = "garcomId", source = "garcom.id")
    @Mapping(target = "garcomNome", source = "garcom.nome")
    MesaShowDTO toShowDTO(MesaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garcom", ignore = true)
    void updateEntity(MesaUpdateDTO dto, @MappingTarget MesaEntity entity);

    MesaDTO toDTO(MesaEntity entity);

    MesaOcuparDTO toMesaOcuparDTO(MesaEntity entity);
}
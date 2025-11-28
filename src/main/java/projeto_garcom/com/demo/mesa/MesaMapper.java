package projeto_garcom.com.demo.mesa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.mesa.dto.MesaRequestDTO;
import projeto_garcom.com.demo.mesa.dto.MesaShowDTO;
import projeto_garcom.com.demo.mesa.dto.MesaUpdateDTO;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garcom", ignore = true)
    MesaEntity toEntity(MesaRequestDTO dto);

    MesaShowDTO toShowDTO(MesaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "garcom", ignore = true)
    void updateEntity(MesaUpdateDTO dto, @MappingTarget MesaEntity entity);
}
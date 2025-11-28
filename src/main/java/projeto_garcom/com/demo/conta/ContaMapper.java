package projeto_garcom.com.demo.conta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.conta.dto.ContaRequestDTO;
import projeto_garcom.com.demo.conta.dto.ContaShowDTO;
import projeto_garcom.com.demo.conta.dto.ContaUpdateDTO;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "caixa", ignore = true)
    @Mapping(target = "pagamento", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    ContaEntity toEntity(ContaRequestDTO dto);

    ContaShowDTO toShowDTO(ContaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "caixa", ignore = true)
    @Mapping(target = "pagamento", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    void updateEntity(ContaUpdateDTO dto, @MappingTarget ContaEntity entity);
}

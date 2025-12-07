package projeto_garcom.com.demo.pagamento;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.pagamento.dto.PagamentoDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoRequestDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoShowDTO;
import projeto_garcom.com.demo.pagamento.dto.PagamentoUpdateDTO;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    PagamentoEntity toEntityFromRequest(PagamentoRequestDTO pagamentoRequestDTO);

    PagamentoDTO entityToPagamentoDTO(PagamentoEntity pagamento);
    PagamentoShowDTO entityToPagamentoShowDTO(PagamentoEntity pagamento);

    @Mapping(target = "id", ignore = true)
    void updatePagamentoFromUpdateDTO(PagamentoUpdateDTO dto, @MappingTarget PagamentoEntity pagamento);
}
package projeto_garcom.com.demo.conta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import projeto_garcom.com.demo.conta.dto.ContaRequestDTO;
import projeto_garcom.com.demo.conta.dto.ContaShowDTO;
import projeto_garcom.com.demo.conta.dto.ContaUpdateDTO;
import projeto_garcom.com.demo.pagamento.PagamentoMapper;

@Mapper(
        componentModel = "spring",
        uses = { PagamentoMapper.class }
)
public interface ContaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "caixa", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    ContaEntity toEntity(ContaRequestDTO dto);

    @Mapping(target = "mesaId", source = "mesa.id")
    @Mapping(target = "caixaId", source = "caixa.id")
    @Mapping(target = "caixaNome", source = "caixa.nome")
    @Mapping(target = "totalConta", expression = "java(entity.getTotalConta())")
    @Mapping(target = "totalPago", expression = "java(entity.getTotalPago())")
    @Mapping(target = "saldoRestante", expression = "java(entity.getSaldoRestante())")
    @Mapping(target = "pagamentos", source = "pagamentos")
    ContaShowDTO toShowDTO(ContaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "caixa", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    void updateEntity(ContaUpdateDTO dto, @MappingTarget ContaEntity entity);
}

package projeto_garcom.com.demo.conta.dto;

import java.util.List;

public record ContaUpdateDTO(
        String nome,
        Long mesaId,
        Long caixaId,
        List<Long> pedidosIds,
        Long pagamentoId
) {}
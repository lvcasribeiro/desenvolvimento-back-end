package projeto_garcom.com.demo.conta.dto;

public record ContaShowDTO(
        Long id,
        String nome,
        Long mesaId,
        Long caixaId,
        String caixaNome
) {}

package projeto_garcom.com.demo.mesa.dto;

public record MesaUpdateDTO(
        Long id,
        Integer numero,
        Boolean disponivel
) {}
package projeto_garcom.com.demo.mesa.dto;

public record MesaUpdateDTO(
        Integer numero,
        Boolean disponivel,
        Long garcomId
) {}
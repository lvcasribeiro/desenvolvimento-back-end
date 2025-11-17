package projeto_garcom.com.demo.cliente.dto;

import java.time.LocalDateTime;

public record ClienteRequestDTO(
        String nome,
        LocalDateTime horaChegada,
        LocalDateTime horaSaida
) {}
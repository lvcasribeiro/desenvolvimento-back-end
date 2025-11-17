package projeto_garcom.com.demo.cliente;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente", schema = "restaurante")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "hora_chegada", nullable = false)
    private LocalDateTime horaChegada;

    @Column(name = "hora_saida", nullable = false)
    private LocalDateTime horaSaida;
}
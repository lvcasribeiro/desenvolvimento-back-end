package projeto_garcom.com.demo.pedido;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido", schema = "restaurante")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "horario_pedido", nullable = false)
    private LocalDateTime horarioPedido;

    @Column(name = "horario_entrega", nullable = false)
    private LocalDateTime horarioEntrega;
}
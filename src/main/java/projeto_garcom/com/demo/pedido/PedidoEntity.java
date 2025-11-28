package projeto_garcom.com.demo.pedido;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.cliente.ClienteEntity;
import projeto_garcom.com.demo.item_pedido.ItemPedidoEntity;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_pedido_id")
    private List<ItemPedidoEntity> itensPedido;
}
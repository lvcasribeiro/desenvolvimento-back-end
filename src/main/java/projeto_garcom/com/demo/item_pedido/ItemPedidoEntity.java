package projeto_garcom.com.demo.item_pedido;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.pedido.PedidoEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_pedido", schema = "restaurante")
public class ItemPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_cardapio_id")
    private PedidoEntity item_cardapio;
}
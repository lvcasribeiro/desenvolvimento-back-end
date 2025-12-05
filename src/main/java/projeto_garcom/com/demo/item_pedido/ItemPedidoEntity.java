package projeto_garcom.com.demo.item_pedido;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;
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

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_cardapio_id")
    private ItemCardapioEntity itemCardapio;
}

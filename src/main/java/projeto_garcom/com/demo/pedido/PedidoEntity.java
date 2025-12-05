package projeto_garcom.com.demo.pedido;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.cliente.ClienteEntity;
import projeto_garcom.com.demo.conta.ContaEntity;
import projeto_garcom.com.demo.item_pedido.ItemPedidoEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private LocalDateTime horarioPedido;

    private LocalDateTime horarioEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itensPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaEntity conta;
}

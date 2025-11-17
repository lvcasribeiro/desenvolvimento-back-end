package projeto_garcom.com.demo.conta;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.caixa.CaixaEntity;
import projeto_garcom.com.demo.mesa.MesaEntity;
import projeto_garcom.com.demo.pagamento.PagamentoEntity;
import projeto_garcom.com.demo.pedido.PedidoEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta", schema = "restaurante")
public class ContaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private MesaEntity mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caixa_id")
    private CaixaEntity caixa;
}
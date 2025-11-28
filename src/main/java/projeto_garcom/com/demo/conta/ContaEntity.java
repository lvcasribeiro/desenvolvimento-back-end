package projeto_garcom.com.demo.conta;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.mesa.MesaEntity;
import projeto_garcom.com.demo.pagamento.PagamentoEntity;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.usuario.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "aberta", nullable = false)
    private Boolean aberta;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoEntity> pedidos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mesa_id", nullable = false)
    private MesaEntity mesa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "caixa_id", nullable = false)
    private UsuarioEntity caixa;

}
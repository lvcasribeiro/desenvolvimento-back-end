package projeto_garcom.com.demo.conta;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.mesa.MesaEntity;
import projeto_garcom.com.demo.pagamento.PagamentoEntity;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.usuario.UsuarioEntity;

import java.math.BigDecimal;
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

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagamentoEntity> pagamentos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mesa_id", nullable = false)
    private MesaEntity mesa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "caixa_id", nullable = false)
    private UsuarioEntity caixa;

    @Column(name = "total", nullable = false)
    private BigDecimal total = BigDecimal.ZERO;


    public BigDecimal getTotalConta() {
        return this.total != null ? this.total : BigDecimal.ZERO;
    }

    public BigDecimal getTotalPago() {
        if (this.pagamentos == null) return BigDecimal.ZERO;

        return this.pagamentos.stream()
                .map(PagamentoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSaldoRestante() {
        return getTotalConta().subtract(getTotalPago());
    }
}
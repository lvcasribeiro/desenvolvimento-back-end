package projeto_garcom.com.demo.pagamento;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.conta.ContaEntity;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagamento", schema = "restaurante")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_transacao", nullable = true)
    private Integer nroTransacao;

    @Column(name = "numero", nullable = true)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private PagamentoEnum tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaEntity conta;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
}
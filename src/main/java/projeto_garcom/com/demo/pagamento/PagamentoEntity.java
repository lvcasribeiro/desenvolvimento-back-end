package projeto_garcom.com.demo.pagamento;

import jakarta.persistence.*;
import lombok.*;

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
}
package projeto_garcom.com.demo.item_cardapio;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.cardapio.CardapioEntity;
import projeto_garcom.com.demo.categoria.CategoriaEntity;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_cardapio", schema = "restaurante")
public class ItemCardapioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ingredientes", nullable = false)
    private String ingredientes;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "disponivel_na_cozinha", nullable = false)
    private Boolean disponivelNaCozinha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private CardapioEntity cardapio;

}
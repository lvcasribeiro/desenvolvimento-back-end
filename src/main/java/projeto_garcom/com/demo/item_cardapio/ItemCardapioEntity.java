package projeto_garcom.com.demo.item_cardapio;

import jakarta.persistence.*;
import lombok.*;

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
}
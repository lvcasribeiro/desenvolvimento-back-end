package projeto_garcom.com.demo.cardapio;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardapio", schema = "restaurante")
public class CardapioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cardapio", fetch = FetchType.LAZY)
    private List<ItemCardapioEntity> itens;
}
package projeto_garcom.com.demo.cardapio;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import projeto_garcom.com.demo.item_cardapio.ItemCardapioEntity;

public class CardapioSpecification {

    public static Specification<CardapioEntity> disponivelNaCozinha(Boolean disponivel) {
        return (root, query, cb) -> {
            if (disponivel == null) return null;

            Join<CardapioEntity, ItemCardapioEntity> itens = root.join("itens", JoinType.LEFT);

            return cb.equal(itens.get("disponivelNaCozinha"), disponivel);
        };
    }
}

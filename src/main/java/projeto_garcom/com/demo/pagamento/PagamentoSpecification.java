package projeto_garcom.com.demo.pagamento;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PagamentoSpecification {
    public static Specification<PagamentoEntity> byTipoPagamento(String tipoPagamento) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(tipoPagamento)) {
                return builder.like(builder.lower(root.get("tipoPagamento")), "%" + tipoPagamento.toLowerCase() + "%");
            }

            return builder.conjunction();
        };
    }
}
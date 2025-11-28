package projeto_garcom.com.demo.conta;

import org.springframework.data.jpa.domain.Specification;

public class ContaSpecification {

    public static Specification<ContaEntity> byNome(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }
}
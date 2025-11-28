package projeto_garcom.com.demo.usuario;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UsuarioSpecification {
    public static Specification<UsuarioEntity> byNome(String nome) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(nome)) {
                return builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
            }

            return builder.conjunction();
        };
    }
}
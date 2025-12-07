package projeto_garcom.com.demo.mesa;

import org.springframework.data.jpa.domain.Specification;

public class MesaSpecification {

    public static Specification<MesaEntity> disponivel(Boolean disponivel) {
        return (root, query, cb) -> {
            if (disponivel == null) return cb.conjunction();
            return cb.equal(root.get("disponivel"), disponivel);
        };
    }

    public static Specification<MesaEntity> garcom(Long garcomId) {
        return (root, query, cb) -> {
            if (garcomId == null) return cb.conjunction();
            return cb.equal(root.get("garcom").get("id"), garcomId);
        };
    }
}
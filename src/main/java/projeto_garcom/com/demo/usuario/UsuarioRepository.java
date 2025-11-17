package projeto_garcom.com.demo.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
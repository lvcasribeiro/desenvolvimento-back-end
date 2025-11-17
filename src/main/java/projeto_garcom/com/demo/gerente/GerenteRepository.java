package projeto_garcom.com.demo.gerente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<GerenteEntity, Long>, JpaSpecificationExecutor<GerenteEntity> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
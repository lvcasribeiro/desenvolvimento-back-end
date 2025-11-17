package projeto_garcom.com.demo.cozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<CozinhaEntity, Long>, JpaSpecificationExecutor<CozinhaEntity> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
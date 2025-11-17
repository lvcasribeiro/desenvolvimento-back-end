package projeto_garcom.com.demo.caixa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long>, JpaSpecificationExecutor<CaixaEntity> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
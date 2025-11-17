package projeto_garcom.com.demo.garcom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GarcomRepository extends JpaRepository<GarcomEntity, Long>, JpaSpecificationExecutor<GarcomEntity> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);
}
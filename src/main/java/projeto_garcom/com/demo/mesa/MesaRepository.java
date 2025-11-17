package projeto_garcom.com.demo.mesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<MesaEntity, Long>, JpaSpecificationExecutor<MesaEntity> {

}
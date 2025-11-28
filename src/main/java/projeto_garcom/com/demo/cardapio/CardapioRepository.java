package projeto_garcom.com.demo.cardapio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioEntity, Long>, JpaSpecificationExecutor<CardapioEntity> {

}
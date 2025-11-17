package projeto_garcom.com.demo.item_cardapio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long>, JpaSpecificationExecutor<ItemCardapioEntity> {

}
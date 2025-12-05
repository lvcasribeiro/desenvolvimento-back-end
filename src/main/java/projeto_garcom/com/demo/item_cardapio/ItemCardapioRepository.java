package projeto_garcom.com.demo.item_cardapio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {

    List<ItemCardapioEntity> findByCategoriaId(Long categoriaId);

    List<ItemCardapioEntity> findByCardapioId(Long cardapioId);

    List<ItemCardapioEntity> findByDisponivelNaCozinhaTrue();
}

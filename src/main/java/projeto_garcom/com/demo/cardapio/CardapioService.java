package projeto_garcom.com.demo.cardapio;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardapioService {

    private final CardapioRepository cardapioRepository;

    public Page<CardapioEntity> listar(int page, int perPage, Boolean disponivelNaCozinha) {

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0),
                perPage > 0 ? perPage : 20);

        Specification<CardapioEntity> spec =
                CardapioSpecification.disponivelNaCozinha(disponivelNaCozinha);

        return cardapioRepository.findAll(spec, pageable);
    }
}
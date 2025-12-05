package projeto_garcom.com.demo.item_cardapio;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.cardapio.CardapioEntity;
import projeto_garcom.com.demo.cardapio.CardapioRepository;
import projeto_garcom.com.demo.categoria.CategoriaEntity;
import projeto_garcom.com.demo.categoria.CategoriaRepository;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioRequestDTO;
import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemCardapioService {

    private final ItemCardapioRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final CardapioRepository cardapioRepository;
    private final ItemCardapioMapper mapper;


    public ItemCardapioResponseDTO criar(ItemCardapioRequestDTO dto) {

        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        CardapioEntity cardapio = cardapioRepository.findById(dto.cardapioId())
                .orElseThrow(() -> new NotFoundException("Cardápio não encontrado"));

        ItemCardapioEntity entity = mapper.toEntity(dto, categoria, cardapio);
        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ItemCardapioResponseDTO atualizar(Long id, ItemCardapioRequestDTO dto) {

        ItemCardapioEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item de cardápio não encontrado"));

        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        CardapioEntity cardapio = cardapioRepository.findById(dto.cardapioId())
                .orElseThrow(() -> new NotFoundException("Cardápio não encontrado"));

        mapper.updateEntity(entity, dto, categoria, cardapio);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public void ativar(Long id) {
        ItemCardapioEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));

        entity.setDisponivelNaCozinha(true);
        repository.save(entity);
    }

    public void desativar(Long id) {
        ItemCardapioEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));

        entity.setDisponivelNaCozinha(false);
        repository.save(entity);
    }

    public ItemCardapioResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Item de cardápio não encontrado"));
    }

    public List<ItemCardapioResponseDTO> listarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ItemCardapioResponseDTO> listarDisponiveis() {
        return repository.findByDisponivelNaCozinhaTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}

package projeto_garcom.com.demo.cardapio.dto;

import projeto_garcom.com.demo.item_cardapio.dto.ItemCardapioResponseDTO;

import java.util.List;

public record CardapioDTO(
        Long id,
        List<ItemCardapioResponseDTO> itens
) {}

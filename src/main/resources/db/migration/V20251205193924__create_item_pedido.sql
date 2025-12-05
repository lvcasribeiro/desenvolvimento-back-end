CREATE TABLE restaurante.item_pedido (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,

    pedido_id BIGINT NOT NULL,
    item_cardapio_id BIGINT NOT NULL,

    CONSTRAINT fk_itempedido_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES restaurante.pedido(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_itempedido_itemcardapio
        FOREIGN KEY (item_cardapio_id)
        REFERENCES restaurante.item_cardapio(id)
);
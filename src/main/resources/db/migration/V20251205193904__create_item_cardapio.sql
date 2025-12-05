CREATE TABLE restaurante.item_cardapio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ingredientes TEXT NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    disponivel_na_cozinha BOOLEAN NOT NULL DEFAULT TRUE,
    categoria_id BIGINT NOT NULL,
    cardapio_id BIGINT NOT NULL,

    CONSTRAINT fk_item_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES restaurante.categoria(id),

    CONSTRAINT fk_item_cardapio
        FOREIGN KEY (cardapio_id)
        REFERENCES restaurante.cardapio(id)
);

-- Mock de itens
INSERT INTO restaurante.item_cardapio
(nome, ingredientes, preco, disponivel_na_cozinha, categoria_id, cardapio_id)
VALUES
('X-Burger', 'Pão, Hambúrguer, Queijo, Maionese', 18.90, TRUE, 1, 1),
('X-Salada', 'Pão, Hambúrguer, Queijo, Alface, Tomate', 20.90, TRUE, 1, 1),
('Coca-Cola Lata', 'Bebida de cola', 6.00, TRUE, 2, 1),
('Pizza Calabresa', 'Molho, Mussarela, Calabresa, Orégano', 45.00, TRUE, 3, 1),
('Pudim', 'Leite, Ovos, Açúcar', 12.00, TRUE, 4, 1);

CREATE TABLE restaurante.categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Mock de categorias
INSERT INTO restaurante.categoria (nome) VALUES
('Lanches'),
('Bebidas'),
('Pizzas'),
('Sobremesas');
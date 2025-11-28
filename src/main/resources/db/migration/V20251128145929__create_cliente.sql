CREATE TABLE IF NOT EXISTS restaurante.cliente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    hora_chegada TIMESTAMP NOT NULL,
    hora_saida TIMESTAMP NOT NULL
);
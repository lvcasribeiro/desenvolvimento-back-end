CREATE TABLE IF NOT EXISTS restaurante.usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    CONSTRAINT unq_login UNIQUE (login)
);
CREATE TABLE IF NOT EXISTS restaurante.mesa (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    disponivel BOOLEAN NOT NULL,
    garcom_id BIGINT,

    CONSTRAINT fk_mesa_garcom
        FOREIGN KEY (garcom_id)
        REFERENCES restaurante.usuario(id)
);

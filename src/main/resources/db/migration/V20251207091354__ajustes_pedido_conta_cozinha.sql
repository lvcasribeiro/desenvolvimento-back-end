CREATE TABLE IF NOT EXISTS restaurante.cozinha (
    id SERIAL PRIMARY KEY
);

ALTER TABLE restaurante.pedido
    ADD COLUMN IF NOT EXISTS status VARCHAR(50) NOT NULL DEFAULT 'RECEBIDO';

ALTER TABLE restaurante.pedido
    ADD COLUMN IF NOT EXISTS cozinha_id BIGINT;

ALTER TABLE restaurante.pedido
    ALTER COLUMN horario_pedido SET NOT NULL;

ALTER TABLE restaurante.pedido
    ADD CONSTRAINT fk_pedido_cozinha
    FOREIGN KEY (cozinha_id)
    REFERENCES restaurante.cozinha(id);

ALTER TABLE restaurante.conta
    ADD COLUMN IF NOT EXISTS total NUMERIC(15,2) NOT NULL DEFAULT 0;

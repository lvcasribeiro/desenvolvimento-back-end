ALTER TABLE restaurante.pedido
ADD COLUMN mesa_id BIGINT;

ALTER TABLE restaurante.pedido
ADD CONSTRAINT fk_pedido_mesa
FOREIGN KEY (mesa_id)
REFERENCES restaurante.mesa(id)
ON UPDATE CASCADE
ON DELETE SET NULL;
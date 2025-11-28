CREATE TABLE IF NOT EXISTS restaurante.pedido (
    id SERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    horario_pedido TIMESTAMP NOT NULL,
    horario_entrega TIMESTAMP NOT NULL,

    cliente_id BIGINT,
    conta_id BIGINT,

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES restaurante.cliente(id),

    CONSTRAINT fk_pedido_conta
        FOREIGN KEY (conta_id)
        REFERENCES restaurante.conta(id)
);

CREATE TABLE IF NOT EXISTS restaurante.conta (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    aberta BOOLEAN NOT NULL,

    pagamento_id BIGINT,
    mesa_id BIGINT NOT NULL,
    caixa_id BIGINT NOT NULL,

    CONSTRAINT fk_conta_pagamento
        FOREIGN KEY (pagamento_id)
        REFERENCES restaurante.pagamento(id),

    CONSTRAINT fk_conta_mesa
        FOREIGN KEY (mesa_id)
        REFERENCES restaurante.mesa(id),

    CONSTRAINT fk_conta_caixa
        FOREIGN KEY (caixa_id)
        REFERENCES restaurante.usuario(id)
);

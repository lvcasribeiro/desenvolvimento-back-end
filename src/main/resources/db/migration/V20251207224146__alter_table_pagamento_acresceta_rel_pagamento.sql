ALTER TABLE restaurante.pagamento
    ADD COLUMN conta_id BIGINT;

ALTER TABLE restaurante.pagamento
    ADD CONSTRAINT fk_pagamento_conta
        FOREIGN KEY (conta_id) REFERENCES restaurante.conta(id);
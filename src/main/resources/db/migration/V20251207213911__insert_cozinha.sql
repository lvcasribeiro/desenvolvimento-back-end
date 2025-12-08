INSERT INTO restaurante.cozinha (id)
SELECT 1
WHERE NOT EXISTS (SELECT 1 FROM restaurante.cozinha WHERE id = 1);
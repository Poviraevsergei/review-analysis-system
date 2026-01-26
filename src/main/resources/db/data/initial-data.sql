-- liquibase formatted sql

-- changeset siarhey.pavirayeu:001-5
-- comment: Adding test user data
INSERT INTO users (id, first_name)
VALUES
    ('b3ff2e39-5c6a-4f6f-a42d-673e070b377b', 'Alex'),
    ('8b88b8e1-ccb1-4908-8526-340ee46c401c', 'Bob')
ON CONFLICT (id) DO NOTHING;

-- changeset siarhey.pavirayeu:001-6
-- comment: Adding test reviews
INSERT INTO review (id, text, rating, location, created, updated, user_id)
VALUES
    ('90eee928-e6a1-4329-b117-a1e68192d986', 'Кофе вкусный', 5, 'Братская 13', '2026-01-20 12:44:07.400000 +00:00', '2026-01-20 09:44:31.890006 +00:00', '8b88b8e1-ccb1-4908-8526-340ee46c401c'),
    ('e21d9dcf-63cd-4c91-8f25-28d829336b4f', 'Не убрано', 2, 'Краснодар, тестовая 5', '2026-01-20 09:45:06.525937 +00:00', '2026-01-20 09:45:06.525937 +00:00', '8b88b8e1-ccb1-4908-8526-340ee46c401c')
ON CONFLICT (id) DO NOTHING;
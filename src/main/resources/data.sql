-- SALE (4 sale cinematografiche)
INSERT INTO sale (numero_posti, nome) VALUES
(150, 'Sala 1 - Premium'),
(120, 'Sala 2 - Standard'),
(80, 'Sala 3 - Piccola'),
(50, 'Sala 4 - VIP');

-- FILM (8 film)
INSERT INTO film (data_uscita, durata, descrizione, regista, titolo) VALUES
(2010, 142, 'Un''avventura spaziale mozzafiato alla scoperta di Marte', 'Marco Bellini', 'Il Pianeta Rosso'),
(2024, 118, 'Un thriller psicologico che tiene con il fiato sospeso', 'Sofia Russo', 'Ombre nella Notte'),
(2004, 105, 'Una commedia brillante sulla vita familiare moderna', 'Luca Fontana', 'La Famiglia Perfetta'),
(1998, 128, 'Una storia d''amore ambientata negli anni ''60', 'Elena Moretti', 'Cuori in Libertà'),
(2010, 156, 'Azione e tecnologia in un futuro distopico', 'Hiroshi Tanaka', 'L''Ultimo Samurai Digitale'),
(1999, 98, 'Una commedia romantica ambientata in Toscana', 'Giovanni Verdi', 'Risate al Tramonto'),
(2009, 134, 'Un mystery drama ambientato su un''isola remota', 'Anna Bianchi', 'Il Mistero del Faro'),
(1988, 92, 'Un film d''animazione per tutta la famiglia', 'Paolo Neri', 'Stelle Cadenti');

-- UTENTI (7 utenti: 1 admin + 5 user + 1 firstaccess)
INSERT INTO utenti (is_first_access, cognome, email, nome, password, recapito_telefonico, ruolo) VALUES
(0, 'Admin', 'admin@admin.com', 'Admin', '$2a$10$mrvVmp3lO.IDj5DZHxE0ROPnhHmVErxyo.EPD/jDMCnI5Rq2eWSHy', '3331122333', 'ROLE_ADMIN'),
(0, 'Rossi', 'mario.rossi@email.com', 'Mario', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3331234567', 'ROLE_USER'),
(0, 'Bianchi', 'laura.bianchi@email.com', 'Laura', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3347654321', 'ROLE_USER'),
(0, 'Verdi', 'giuseppe.verdi@email.com', 'Giuseppe', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3389876543', 'ROLE_USER'),
(0, 'Romano', 'francesca.romano@email.com', 'Francesca', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3391237890', 'ROLE_USER'),
(0, 'Ferrari', 'alessandro.ferrari@email.com', 'Alessandro', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3356789012', 'ROLE_USER'),
(1, 'Conti', 'chiara.conti@email.com', 'Chiara', '$2a$10$guVGQJB3/cT32bu4uQkU5.7Pok7KujYnuf3I5Wb7xGef0GAuQS32C', '3398765432', 'ROLE_FIRSTACCESS');

-- PROIEZIONI (15 proiezioni distribuite tra le varie sale)
INSERT INTO proiezioni (prezzo_biglietto, data_ora_inizio, film_id, sala_id) VALUES
(9.50, '2025-10-23 18:30:00', 1, 1),
(9.50, '2025-10-23 21:00:00', 1, 1),
(8.50, '2025-10-23 19:00:00', 2, 2),
(8.00, '2025-10-23 17:30:00', 3, 3),
(12.00, '2025-10-23 20:30:00', 5, 4),
(9.50, '2025-10-24 18:00:00', 1, 2),
(9.50, '2025-10-24 21:15:00', 2, 1),
(8.00, '2025-10-24 19:30:00', 4, 3),
(8.50, '2025-10-24 17:00:00', 6, 2),
(12.00, '2025-10-24 20:00:00', 7, 4),
(9.50, '2025-10-25 18:45:00', 3, 1),
(8.50, '2025-10-25 21:30:00', 5, 2),
(7.50, '2025-10-25 15:00:00', 8, 3),
(8.00, '2025-10-25 19:15:00', 6, 3),
(9.50, '2025-10-25 22:00:00', 7, 1);

-- PRENOTAZIONI (12 prenotazioni)
INSERT INTO prenotazioni (utente_id, proiezione_id, posti_prenotati) VALUES
(2, 1, 2),
(3, 1, 3),
(4, 2, 1),
(2, 3, 4),
(5, 4, 2),
(6, 5, 2),
(3, 6, 3),
(4, 7, 1),
(5, 8, 2),
(2, 10, 2),
(6, 12, 1),
(3, 13, 5);

-- PRENOTAZIONI (12 prenotazioni)
INSERT INTO prenotazioni (posti_prenotati, proiezione_id, utente_id) VALUES
(2, 1, 2),
(3, 1, 3),
(1, 2, 4),
(4, 3, 2),
(2, 4, 5),
(2, 5, 6),
(3, 6, 3),
(1, 7, 4),
(2, 8, 5),
(2, 10, 2),
(1, 12, 6),
(5, 13, 3);
-- Artistas
INSERT INTO artistas (nome, tipo) VALUES ('Serj Tankian', 'CANTOR');
INSERT INTO artistas (nome, tipo) VALUES ('Mike Shinoda', 'CANTOR');
INSERT INTO artistas (nome, tipo) VALUES ('Michel Teló', 'CANTOR');
INSERT INTO artistas (nome, tipo) VALUES ('Guns N'' Roses', 'BANDA');

-- Albuns
INSERT INTO albuns (titulo) VALUES ('Harakiri');
INSERT INTO albuns (titulo) VALUES ('Black Blooms');
INSERT INTO albuns (titulo) VALUES ('The Rough Dog');
INSERT INTO albuns (titulo) VALUES ('The Rising Tied');
INSERT INTO albuns (titulo) VALUES ('Post Traumatic');
INSERT INTO albuns (titulo) VALUES ('Post Traumatic EP');
INSERT INTO albuns (titulo) VALUES ('Where''d You Go');
INSERT INTO albuns (titulo) VALUES ('Bem Sertanejo');
INSERT INTO albuns (titulo) VALUES ('Bem Sertanejo - O Show (Ao Vivo)');
INSERT INTO albuns (titulo) VALUES ('Bem Sertanejo - (1ª Temporada) - EP');
INSERT INTO albuns (titulo) VALUES ('Use Your Illusion I');
INSERT INTO albuns (titulo) VALUES ('Use Your Illusion II');
INSERT INTO albuns (titulo) VALUES ('Greatest Hits');

-- Relacionamentos Artista-Album
INSERT INTO artista_album (artista_id, album_id) VALUES (1, 1);
INSERT INTO artista_album (artista_id, album_id) VALUES (1, 2);
INSERT INTO artista_album (artista_id, album_id) VALUES (1, 3);
INSERT INTO artista_album (artista_id, album_id) VALUES (2, 4);
INSERT INTO artista_album (artista_id, album_id) VALUES (2, 5);
INSERT INTO artista_album (artista_id, album_id) VALUES (2, 6);
INSERT INTO artista_album (artista_id, album_id) VALUES (2, 7);
INSERT INTO artista_album (artista_id, album_id) VALUES (3, 8);
INSERT INTO artista_album (artista_id, album_id) VALUES (3, 9);
INSERT INTO artista_album (artista_id, album_id) VALUES (3, 10);
INSERT INTO artista_album (artista_id, album_id) VALUES (4, 11);
INSERT INTO artista_album (artista_id, album_id) VALUES (4, 12);
INSERT INTO artista_album (artista_id, album_id) VALUES (4, 13);
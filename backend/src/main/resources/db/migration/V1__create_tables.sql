CREATE TABLE artistas (
                          id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          nome        VARCHAR(200) NOT NULL,
                          tipo        VARCHAR(20)  NOT NULL,
                          created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE albuns (
                        id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        titulo      VARCHAR(300) NOT NULL,
                        created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE artista_album (
                               artista_id  BIGINT NOT NULL REFERENCES artistas(id),
                               album_id    BIGINT NOT NULL REFERENCES albuns(id),
                               PRIMARY KEY (artista_id, album_id)
);

CREATE TABLE album_imagens (
                               id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               album_id    BIGINT       NOT NULL REFERENCES albuns(id),
                               object_key  VARCHAR(500) NOT NULL
);

CREATE TABLE regionais (
                           id    INTEGER      PRIMARY KEY,
                           nome  VARCHAR(200) NOT NULL,
                           ativo BOOLEAN      NOT NULL DEFAULT TRUE
);
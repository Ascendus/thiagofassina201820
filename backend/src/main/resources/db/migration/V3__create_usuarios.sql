CREATE TABLE usuarios (
                          id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          username   VARCHAR(100) NOT NULL UNIQUE,
                          password   VARCHAR(255) NOT NULL,
                          role       VARCHAR(20)  NOT NULL DEFAULT 'ROLE_USER',
                          created_at TIMESTAMP    NOT NULL DEFAULT NOW()
);
INSERT INTO usuarios (username, password, role)
VALUES (
           'admin',
           '$2a$12$k3T9h.iW8z1D5QpB4C0fCOx6K1N2RtMWmPl0V4vFj3bGhYaUfk9Vi',
           'ROLE_USER'
       );
-- Senha em texto plano: admin123
-- Hash gerado com BCryptPasswordEncoder.encode("admin123")
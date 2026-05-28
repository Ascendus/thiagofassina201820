INSERT INTO usuarios (username, password, role)
VALUES (
           'admin',
           '$2a$10$LYR6gse0nsEH6x7Wa45Lye.JiR/7xG2/xNsZSzxa4QlG1Q.PEzj0W',
           'ROLE_USER'
       );
-- Senha em texto plano: admin123
-- Hash gerado com BCryptPasswordEncoder.encode("admin123")
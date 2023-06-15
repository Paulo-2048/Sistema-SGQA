-- Inserir dados na tabela est_estudante
INSERT INTO est_estudante (est_matricula, est_nomecompleto, est_email, est_anoegresso)
VALUES
    ('2021001', 'João Silva', 'joao.silva@example.com', 2021),
    ('2021002', 'Maria Santos', 'maria.santos@example.com', 2021),
    ('2021003', 'Pedro Oliveira', 'pedro.oliveira@example.com', 2021),
    ('2021004', 'Ana Costa', 'ana.costa@example.com', 2021),
    ('2021005', 'Luiza Pereira', 'luiza.pereira@example.com', 2021);

-- Inserir dados na tabela pro_professor
INSERT INTO pro_professor (pro_matricula, pro_nomecompleto, pro_email, pro_anoegresso)
VALUES
    ('P001', 'Carlos Ferreira', 'carlos.ferreira@example.com', 2010),
    ('P002', 'Ana Rodrigues', 'ana.rodrigues@example.com', 2012),
    ('P003', 'Fernando Santos', 'fernando.santos@example.com', 2015),
    ('P004', 'Mariana Almeida', 'mariana.almeida@example.com', 2018),
    ('P005', 'Rafael Oliveira', 'rafael.oliveira@example.com', 2020);


-- Inserir dados na tabela dis_disciplina
INSERT INTO dis_disciplina (dis_codigodisciplina, dis_nome, dis_pro_id)
VALUES
    ('DIS01', 'Matemática', 11),
    ('DIS02', 'Programação', 12),
    ('DIS03', 'Banco de Dados', 13),
    ('DIS04', 'Engenharia de Software', 14),
    ('DIS05', 'Redes de Computadores', 15);


-- Inserir dados na tabela mat_matricula
INSERT INTO mat_matricula (mat_dis_id, mat_est_id)
VALUES
    (11, 11),
    (12, 11),
    (13, 12),
    (14, 13),
    (15, 14);
    
-- Inserir registros na tabela hor_horario
INSERT INTO hor_horario (hor_dis_id, hor_data_inicio, hor_data_final, hor_dia_semana, hor_horario_inicio, hor_horario_final)
VALUES
    (11, '2023-06-01', '2023-06-30', 'SEGUNDA', '08:00:00', '10:00:00'),
    (12, '2023-06-01', '2023-06-30', 'QUARTA', '14:00:00', '16:00:00'),
    (13, '2023-06-01', '2023-06-30', 'QUINTA', '10:30:00', '12:30:00'),
    (14, '2023-06-01', '2023-06-30', 'QUINTA', '16:30:00', '18:30:00'),
    (13, '2023-06-01', '2023-06-30', 'SEXTA', '16:30:00', '18:30:00'),
    (15, '2023-06-01', '2023-06-30', 'QUINTA', '09:00:00', '11:00:00');



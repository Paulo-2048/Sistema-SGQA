-- Tabela est_estudante
CREATE TABLE est_estudante (
    est_id INTEGQER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    est_matricula VARCHAR(20) NOT NULL,
    est_nomecompleto VARCHAR(100) NOT NULL,
    est_email VARCHAR(100) NOT NULL,
    est_anoegresso INTEGER NOT NULL
);

-- Tabela pro_professor
CREATE TABLE pro_professor (
    pro_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pro_matricula VARCHAR(20) NOT NULL,
    pro_nomecompleto VARCHAR(100) NOT NULL,
    pro_email VARCHAR(100) NOT NULL,
    pro_anoegresso INTEGER NOT NULL
);

-- Tabela dis_disciplina
CREATE TABLE dis_disciplina (
 	  dis_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    dis_codigodisciplina VARCHAR(10) NOT NULL,
    dis_nome VARCHAR(100) NOT NULL,
    dis_pro_id INTEGER REFERENCES pro_professor(pro_id) ON DELETE CASCADE
);


-- Tabela mat_matricula
CREATE TABLE mat_matricula (
    mat_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    mat_dis_id INTEGER REFERENCES dis_disciplina(dis_id) ON DELETE CASCADE,
    mat_est_id INTEGER REFERENCES est_estudante(est_id) ON DELETE CASCADE
);

-- Tabela hor_horario
CREATE TABLE hor_horario (
    hor_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    hor_dis_id INTEGER REFERENCES dis_disciplina(dis_id) ON DELETE CASCADE,
    hor_data_inicio DATE NOT NULL,
    hor_data_final DATE NOT NULL,
    hor_dia_semana VARCHAR(20) NOT NULL,
    hor_horario_inicio TIME NOT NULL,
    hor_horario_final TIME NOT NULL
);
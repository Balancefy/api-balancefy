CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(100),
    avatar VARCHAR(255),
    banner VARCHAR(255),
    data_nasc DATE,
    tipo INT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Conta (
    id_conta SERIAL PRIMARY KEY,
    renda DECIMAL(10,2) NOT NULL,
    progresso DECIMAL(10,2) NOT NULL,
    status INT,
    fk_usuario INT NOT NULL,
    FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS Objetivo (
    id_objetivo SERIAL PRIMARY KEY,
    categoria VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS ObjetivoConta (
    id_objetivo_conta SERIAL PRIMARY KEY,
    fk_conta INT NOT NULL,
    fk_objetivo INT NOT NULL,
    descricao VARCHAR(100),
    done INT,
    valor_total DECIMAL(10,2),
    valor_inicial DECIMAL(10,2),
    tempo_estimado TIMESTAMP(0),
    pontuacao DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta),
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS Task (
    id_task SERIAL PRIMARY KEY,
    categoria VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS TaskObjetivo (
    id_task_objetivo SERIAL PRIMARY KEY,
    fk_task INT NOT NULL,
    fk_objetivo INT NOT NULL,
    ordem INT NOT NULL,
    FOREIGN KEY(fk_task) REFERENCES Task (id_task),
    FOREIGN KEY(fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS TaskObjetivoConta (
    descricao VARCHAR(100),
    done INT,
    pontuacao DECIMAL(10,2),
    valor DECIMAL(10,2),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_objetivo_conta INT NOT NULL,
    fk_task_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo_conta) REFERENCES ObjetivoConta (id_objetivo_conta),
    FOREIGN KEY (fk_task_objetivo)  REFERENCES  TaskObjetivo (id_task_objetivo),
    PRIMARY KEY (fk_objetivo_conta, fk_task_objetivo)
);


CREATE TABLE IF NOT EXISTS Movimentacao (
    id_movimentacao SERIAL PRIMARY KEY,
    valor DECIMAL(10,2),
    topico VARCHAR(100),
    descricao VARCHAR(100),
    tipo VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_objetivo_conta INT NOT NULL,
    FOREIGN KEY (fk_objetivo_conta) REFERENCES ObjetivoConta (id_objetivo_conta)
);

CREATE TABLE IF NOT EXISTS MovimentacaoFixa  (
    id_movimentacao_fixa SERIAL PRIMARY KEY,
    categoria VARCHAR(100),
    descricao VARCHAR(100),
    valor DECIMAL(10,2),
    tipo VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Dica (
    id_dica SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL,
    tema VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS Topico (
    id_topico SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    conteudo VARCHAR(255) NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Comentario (
    id_comentario SERIAL PRIMARY KEY,
    conteudo VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    fk_topico INT NOT NULL,
    fk_comentario INT NOT NULL,
    FOREIGN KEY (fk_comentario) REFERENCES Comentario (id_comentario),
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta),
    FOREIGN KEY (fk_topico) REFERENCES Topico (id_topico)
);

CREATE TABLE IF NOT EXISTS Likes (
    fk_topico INT NOT NULL,
    fk_conta INT NOT NULL,
    FOREIGN KEY(fk_topico) REFERENCES Topico (id_topico),
    FOREIGN KEY(fk_conta) REFERENCES Conta (id_conta),
    PRIMARY KEY (fk_topico, fk_conta)
);

INSERT INTO Usuario (nome, email, senha, data_nasc, avatar, tipo, banner)
VALUES('Tobias', 'a@gmail.com', '$2a$10$uEuUTkj3bdPfhHCgzCEi4ePIB5G9pnYORt9IlYwqdWUe72FSoKHpC', '1999-03-22', '../../Images/user2.jpg', 0, '');

INSERT INTO Usuario (nome, email, senha, data_nasc, avatar, tipo)
VALUES('Verdinher', 'b@gmail.com', '$2a$10$5Ly35HJ3FacRf./o9vdci.IZpaevCR72cL4GHcEMIMMMoe.vPh8Wa', '1999-03-22', '../../Images/user2.jpg', 0);

INSERT INTO Conta (renda, progresso, status,fk_usuario)
VALUES(50, 0, 1, 1);

INSERT INTO Conta (renda, progresso, status,fk_usuario)
VALUES(50, 10, 1, 2);


-- INSERT INTO Dica (titulo, descricao, tema)
-- VALUES('EconomizeJA', 'Saiba como economizar 1 milhao', 'Economia');

INSERT INTO MovimentacaoFixa (categoria, descricao, valor, tipo, fk_conta)
VALUES ('Lazer', 'Kart', 50.0, 'Saida', 2),
       ('Emergencia', 'Celular', 600.0, 'Saida', 2),
       ('Renda', 'Salario', 2000.0, 'Entrada', 2),
       ('Renda', 'Ajudinha da Vo', 100.0, 'Entrada', 2);

INSERT INTO Objetivo(categoria)
VALUES ('Viagem Internacional'),
       ('Viagem Nacional'),
       ('Comprar casa própria'),
       ('Comprar carro'),
       ('Faculdade'),
       ('Quitação de Dívida'),
       ('Compras Gerais');


INSERT INTO Task(categoria)
VALUES ('Economizar'),
       ('Procurar Hotel'),
       ('Comprar Malas de Viagem');

INSERT INTO TaskObjetivo(fk_task, fk_objetivo, ordem)
VALUES (1, 1, 1),
       (1, 2, 1),
       (1, 3, 1),
       (1, 4, 1),
       (1, 5, 1),
       (1, 6, 1),
       (1, 7, 1),
       (2, 1, 2),
       (3, 1, 3);

INSERT INTO ObjetivoConta ( fk_conta, fk_objetivo, descricao, done, valor_total, valor_inicial, tempo_estimado, pontuacao)
VALUES(1, 1, 'Viagem Australia', 0, 200.0, 10.0, current_timestamp, 1000.0);

INSERT INTO TaskObjetivoConta (descricao, done, pontuacao, fk_objetivo_conta, fk_task_objetivo, valor)
VALUES('Economizar', 0, 300.0, 1, 1, 10),
    ('Procurar Hotel', 0, 500.0, 1, 8, 0),
    ('Comprar Malas de Viagem', 0, 700.0, 1, 9, 100);

INSERT INTO Movimentacao (valor, topico, descricao, tipo, fk_objetivo_conta)
VALUES(50.0, 'Lazer', 'Kart','Saida', 1),
    (30.0, 'Lazer', 'Futebol','Saida', 1),
    (80.0, 'Comida', 'Japa','Saida', 1),
    (550.0, 'Lazer', 'Roupas','Saida', 1),
    (250.0, 'Dinheiro', 'Dinheiro da Vó','Entrada', 1),
    (375.0, 'Lazer', 'Cartas de Magic','Saida', 1),
    (575.0, 'Dinheiro', 'Emprestimo para o jogo do Flamengo','Entrada', 1),
    (320.0, 'Lazer', '13000 de RP','Saida', 1),
    (45.0, 'Lazer', 'Pizza Marguerita','Saida', 1);

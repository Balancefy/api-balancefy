CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    avatar VARCHAR(255),
    banner VARCHAR(255),
    data_nasc DATE,
    status INT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Rank (
    id_rank SERIAL PRIMARY KEY,
    posicao INT,
    fk_usuario INT NOT NULL,
    FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario)
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
    nome VARCHAR(100),
    descricao VARCHAR(100),
    done INT,
    valor_total DECIMAL(10,2),
    valor_inicial DECIMAL(10,2),
    tempo_estimado TIMESTAMP(0),
    pontuacao DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Task (
    id_task SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao VARCHAR(100),
    done INT,
    pontuacao DECIMAL(10,2),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS Movimentacao (
    id_movimentacao SERIAL PRIMARY KEY,
    valor DECIMAL(10,2),
    topico VARCHAR(100),
    descricao VARCHAR(100),
    tipo VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
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
    liked INT NULL,
    viewed INT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Comentario (
    id_comentario SERIAL PRIMARY KEY,
    liked INT,
    viewed INT NULL,
    conteudo VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fk_conta INT NOT NULL,
    fk_topico INT NOT NULL,
    fk_comentario INT NOT NULL,
    FOREIGN KEY (fk_comentario) REFERENCES Comentario (id_comentario),
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta),
    FOREIGN KEY (fk_topico) REFERENCES Topico (id_topico)
);

INSERT INTO Usuario (nome, email, senha, data_nasc, status)
VALUES('Uesli', 'a@gmail.com', 'Senhaboa1*', '1999-03-22', 1);

INSERT INTO Usuario (nome, email, senha, data_nasc, status)
VALUES('Verdinher', 'b@gmail.com', 'Senhaboa1*', '2009-03-22', 1);

INSERT INTO Conta (renda, progresso, fk_usuario)
VALUES(50, 0, 1);

INSERT INTO Dica (titulo, descricao, tema)
VALUES('EconomizeJA', 'Saiba como economizar 1 milhao', 'Economia');

INSERT INTO Objetivo (nome, descricao, done, valor_total, valor_inicial, tempo_estimado, pontuacao, fk_conta)
VALUES('Carro', 'Quero comprar meu gol quadrado', 0, 13000, 2000, current_timestamp, 20, 1);

INSERT INTO Task (nome, descricao, done, pontuacao, fk_objetivo)
VALUES('JuntarDinDin', 'Comprar a roda', 0, 0.0, 1);

INSERT INTO Movimentacao (valor, topico, descricao, tipo, fk_objetivo)
VALUES(50.0, 'Lazer', 'Kart','Saida', 1);

INSERT INTO MovimentacaoFixa (categoria, descricao, valor, tipo, fk_conta)
    VALUES ('Lazer', 'Kart', 50.0, 'Saida', 1),
           ('Emergencia', 'Celular', 600.0, 'Saida', 1),
           ('Renda', 'Salario', 2000.0, 'Entrada', 1),
           ('Renda', 'Ajudinha da Vo', 100.0, 'Entrada', 1);

INSERT INTO Movimentacao (valor, topico, descricao, tipo, fk_objetivo)
VALUES(50.0, 'Renda', 'Mesada', 'Entrada', 1);
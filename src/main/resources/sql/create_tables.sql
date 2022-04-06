CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    avatar VARCHAR(255),
    banner VARCHAR(255),
    data_nasc DATE
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
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Task (
    id_task SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao VARCHAR(100),
    done INT,
    pontuacao DECIMAL(10,2),
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS Movimentacao (
    id_movimentacao SERIAL PRIMARY KEY,
    categoria VARCHAR(100),
    descricao VARCHAR(100),
    valor DECIMAL(10,2),
    tipo VARCHAR(100),
    create_at TIMESTAMP(0) NULL,
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS MovimentacaoFixa  (
    id_movimentacao_fixa SERIAL PRIMARY KEY,
    categoria VARCHAR(100),
    descricao VARCHAR(100),
    valor DECIMAL(10,2),
    tipo VARCHAR(100),
    create_at TIMESTAMP(0) NULL,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Dica (
    id_dica SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL,
    tema VARCHAR(45) NULL,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Topico (
    id_topico SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    conteudo VARCHAR(255) NULL,
    like INT NULL,
    view INT NULL,
    create_at TIMESTAMP(0),
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Comentario (
    id_comentario SERIAL PRIMARY KEY,
    like INT,
    view INT NULL,
    conteudo VARCHAR(255),
    create_at TIMESTAMP(0),
    fk_conta INT NOT NULL,
    fk_topico INT NOT NULL,
    fk_comentario INT NOT NULL,
    FOREIGN KEY (fk_comentario) REFERENCES Comentario (id_comentario),
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta),
    FOREIGN KEY (fk_topico) REFERENCES Topico (id_topico)
);

INSERT INTO Usuario (nome, email, senha, data_nasc)
VALUES('Uesli', 'a@gmail.com', 'senhaboa', '1999-03-22');

INSERT INTO Usuario (nome, email, senha, data_nasc)
VALUES('Verdinher', 'b@gmail.com', 'senhaboa', '2009-03-22');

INSERT INTO Conta (renda, progresso, fk_usuario)
VALUES(50, 0, 1);

INSERT INTO Dica (titulo, descricao, tema, fk_conta)
VALUES('EconomizeJA', 'Saiba como economizar 1 milhao', 'Economia', 1);

INSERT INTO Objetivo (nome, descricao, done, valor_total, valor_inicial, tempo_estimado, pontuacao, fk_conta)
VALUES('Carro', 'Quero comprar meu gol quadrado', 0, 13000, 2000, current_timestamp, 20, 1);

INSERT INTO Task (nome, descricao, done, qtd_desconto, fk_objetivo)
VALUES('JuntarDinDin', 'Comprar a roda', 0, 0.0, 1);


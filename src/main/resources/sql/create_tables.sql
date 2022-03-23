CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    profile_image VARCHAR(100),
    banner VARCHAR(100),
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
    qtd_desconto DECIMAL(10,2),
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS Movimentacao (
    id_movimentacao SERIAL PRIMARY KEY,
    valor DECIMAL(10,2),
    topico VARCHAR(100),
    descricao VARCHAR(100),
    tipo VARCHAR(100),
    create_at TIMESTAMP(0) NULL,
    fk_objetivo INT NOT NULL,
    FOREIGN KEY (fk_objetivo) REFERENCES Objetivo (id_objetivo)
);

CREATE TABLE IF NOT EXISTS MovimentacaoFixa  (
    id_movimentacao_fixa SERIAL PRIMARY KEY,
    valor DECIMAL(10,2),
    topico VARCHAR(100),
    descricao VARCHAR(100),
    tipo VARCHAR(100),
    create_at TIMESTAMP(0) NULL,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Dica (
    id_dicas SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL,
    tema VARCHAR(45) NULL,
    fk_conta INT NOT NULL,
    FOREIGN KEY (fk_conta) REFERENCES Conta (id_conta)
);

CREATE TABLE IF NOT EXISTS Topico (
    id_topico SERIAL PRIMARY KEY,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS Comentario (
    id_comentario SERIAL PRIMARY KEY,
    curtida INT,
    visualizacao INT NULL,
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
VALUES('minokyo', 'a@gmail.com', 'senhaboa', '1999-03-22');

INSERT INTO Usuario (nome, email, senha, data_nasc)
VALUES('matuais', 'b@gmail.com', 'senhaboa', '2009-03-22');
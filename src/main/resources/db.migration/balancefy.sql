CREATE SCHEMA IF NOT EXISTS balancefy DEFAULT CHARACTER SET utf8 ;
USE balancefy ;


CREATE TABLE Usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NULL,
    email VARCHAR(100) NULL,
    senha VARCHAR(100) NULL,
    data_nasc DATE NULL,
    PRIMARY KEY (id_usuario))
;

CREATE TABLE Rank (
    id_rank INT NOT NULL AUTO_INCREMENT,
    posicao INT NULL,
    fk_usuario INT NOT NULL,
    PRIMARY KEY (id_rank),
    FOREIGN KEY (fk_usuario)
    REFERENCES balancefy.Usuario (id_usuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE Conta (
    id_conta INT NOT NULL AUTO_INCREMENT,
    saldo DOUBLE NOT NULL,
    renda DOUBLE NOT NULL,
    qtd_objetivos_completos INT NULL,
    progresso DOUBLE NOT NULL,
    fk_usuario INT NOT NULL,
    PRIMARY KEY (id_conta),
    CONSTRAINT fk_Conta_Usuario1
    FOREIGN KEY (fk_usuario)
    REFERENCES balancefy.Usuario (id_usuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE Objetivo (
    id_objetivo INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NULL,
    descricao VARCHAR(100) NULL,
    done INT NULL,
    valorTotal DOUBLE NULL,
    valorInicial DOUBLE NULL,
    tempo_estimado DATETIME(0) NULL,
    fkConta INT NOT NULL,
    PRIMARY KEY (id_objetivo),
    CONSTRAINT fk_Objetivo_Conta1
    FOREIGN KEY (fkConta)
    REFERENCES balancefy.Conta (id_conta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE Movimentacao (
    id_movimentacao INT NOT NULL AUTO_INCREMENT,
    valor_total DOUBLE NULL,
    tipo VARCHAR(100) NULL,
    create_at DATETIME(0) NULL,
    fk_objetivo INT NOT NULL,
    fk_conta INT NOT NULL,
    PRIMARY KEY (id_movimentacao),
    CONSTRAINT fk_Movimentacao_Objetivo1
    FOREIGN KEY (fk_objetivo)
    REFERENCES balancefy.Objetivo (id_objetivo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_Movimentacao_Conta1
    FOREIGN KEY (fk_conta)
    REFERENCES balancefy.Conta (id_conta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE Task (
    id_task INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NULL,
    descricao VARCHAR(100) NULL,
    done INT NULL,
    qtd_desconto DOUBLE NULL,
    fk_objetivo INT NOT NULL,
    PRIMARY KEY (id_task),
    FOREIGN KEY (fk_objetivo)
    REFERENCES balancefy.Objetivo (id_objetivo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE Topico (
    id_topico INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL,
    PRIMARY KEY (id_topico))
;


-- SQLINES DEMO *** OR EVALUATION USE ONLY
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE Dica (
    id_dicas INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NULL,
    descricao VARCHAR(255) NULL,
    tema VARCHAR(45) NULL,
    fk_conta INT NOT NULL,
    PRIMARY KEY (id_dicas),
    FOREIGN KEY (fk_conta)
    REFERENCES balancefy.Conta (id_conta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- SQLINES DEMO *** OR EVALUATION USE ONLY
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE Comentario (
    id_comentario INT NOT NULL AUTO_INCREMENT,
    qtd_curtidas INT NULL,
    qtdVisualizacoes INT NULL,
    data DATETIME(0) NULL,
    conteudo VARCHAR(255) NULL,
    fk_conta INT NOT NULL,
    fk_topico INT NOT NULL,
    fk_comentario INT NOT NULL,
    PRIMARY KEY (id_comentario),
    FOREIGN KEY (fk_comentario)
    REFERENCES balancefy.Comentario (id_comentario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_Comentario_Conta1
    FOREIGN KEY (fk_conta)
    REFERENCES balancefy.Conta (id_conta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_Comentario_Topico1
    FOREIGN KEY (fk_topico)
    REFERENCES balancefy.Topico (id_topico)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO Usuario (nome, email, senha, data_nasc)
VALUES('minokyo', 'a@gmail.com', 'senhaboa', '1999-03-22');

INSERT INTO Usuario (nome, email, senha, data_nasc)
VALUES('matuais', 'b@gmail.com', 'senhaboa', '2009-03-22');
CREATE DATABASE mercado;

CREATE TABLE produto (
    idproduto int NOT NULL,
    nomeproduto varchar(100) NOT NULL,
    precoproduto float NOT NULL,
    PRIMARY KEY (idproduto)
);
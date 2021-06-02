CREATE DATABASE sistemalivraria;
USE sistemalivraria;

CREATE TABLE funcionarios(
id int(4) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
senha varchar(5) NOT NULL,
primary key (id)
);

CREATE TABLE clientes(
id int(4) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
senha varchar(5) NOT NULL,
primary key (id)
);

CREATE TABLE livros(
id int(4) AUTO_INCREMENT,
isbn int(4) NOT NULL,
titulo varchar(30) NOT NULL,
autor varchar(30) NOT NULL,
editora varchar(30) NOT NULL,
valor decimal(5,2) NOT NULL,
qtdadeExemplares int(4) NOT NULL,
primary key (id)
);



insert into funcionarios (nome, senha) values ('funci', '1234');

insert into clientes (nome, senha) values ('cli', '1234');

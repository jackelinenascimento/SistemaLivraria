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

insert into funcionarios (nome, senha) values ('funcionario', '1234');

insert into clientes (nome, senha) values ('cliente', '1234');

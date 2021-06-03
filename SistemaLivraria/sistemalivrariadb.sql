CREATE DATABASE sistemalivraria;
USE sistemalivraria;

CREATE TABLE funcionarios(
id int(4) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
usuario varchar(10) NOT NULL,
senha varchar(5) NOT NULL,
primary key (id)
);

CREATE TABLE clientes(
id int(4) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
usuario varchar(10) NOT NULL,
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
qtdeExemplares int(4) NOT NULL,
primary key (id)
);



insert into funcionarios (nome, usuario, senha) values ('funcionario', 'f', '1234');

insert into clientes (nome, usuario, senha) values ('cliente', 'c', '1234');

insert into livros (isbn, titulo, autor, editora, valor, qtdeExemplares) values ('123', 'Orgulho e Preconceito', 'Jane Austen', 'Editora', '15.70', '50');

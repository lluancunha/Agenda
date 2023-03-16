/**
Agenda de contatos
@author Luan Cunha
*/

/* banco de dados */
-- exibir os bancos de dados do servidor
show databases;
-- criar um novo bando de dados
create database dbagenda;
-- selecionar o banco de dados
use dbagenda;
-- excluir um banco de dados
drop database dbagenda;


/** tabela **/
-- int tipo de dados (numero inteiro)
-- primary key (PK) "chave primária"
-- auto_increment (numeração automatica)
-- varchar(30) tipo de dados String (máximo de caracteres)
-- not null (validação campo obrigatorio)

create table contatos 
( id int primary key auto_increment, 
nome varchar(30) not null,
fone varchar(15) not null,
email varchar(30) );

-- para descrever a tabela usamos o comando describe
describe contatos;

-- para verificar as tabelas usamos o comando show tables
show tables;

-- alterando o nome de um campo da tabela
alter table contatos change nome contato varchar(30) not null;
alter table contatos change fone telefone varchar(15) not null;


-- adicionando um campo a tabela 
alter table contatos add column obs varchar(250);

-- adicionando um campo a tabela em um local especifico
alter table contatos add column fone2 varchar(15) after telefone;

-- modificando um atributo (tipo de dados, validação etc)
alter table contatos modify column fone2 varchar(15) not null;
alter table contatos modify column contato varchar(50) not null;

-- apgar um campo da tabela
alter table contatos drop column obs;

-- apagar tabela 
drop table contatos;

/*******************************************************/
-- CRUD [create]
insert into contatos (nome,fone,email)
values('Luan Cunha','99999-1111','luan.cunha@gmail.com');
insert into contatos (nome,fone)
values('Bill Gates','99999-2222');
insert into contatos (nome,fone,email)
values('Manoel gomes','7777-7777','caneta.azul@gmail.com');

/* exemplos de erros */
insert into contatos (nome,email)
values('Naldo Beny','naldo.beny@gmail.com');

insert into contatos (nome,fone,email)
values('Naldo Beny', '9999-9999','naldo.beny@gmail.com');




select * from contatos;

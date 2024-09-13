CREATE DATABASE dbagenda;
SHOW DATABASES;
USE dbagenda;

CREATE TABLE contatos(
	idcon int PRIMARY KEY auto_increment,
    nome VARCHAR(50) NOT NULL,
    fone VARCHAR(50) NOT NULL,
    email VARCHAR(50)
);

SHOW TABLES;
DESCRIBE contatos;

/*
SELECT table_name, column_name, data_type
FROM information_schema.columns
WHERE table_schema = 'dbagenda';
*/
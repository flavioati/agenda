use dbagenda;

/* CRUD CREATE */
INSERT INTO contatos (nome, fone, email) VALUES ('Bill Gates', '99999-1111', 'bill-gates@microsoft.com');

/* CRUD READ */
SELECT * FROM contatos;
SELECT * FROM contatos ORDER BY nome;

/* CRUD UPDATE */
UPDATE contatos SET nome="F", fone="99999-9999", email="f@email.com" WHERE idcon=1;

/* CRUD DELETE */
DELETE FROM contatos WHERE idcon=1;
CREATE DATABASE sandro_pastelaria;
use sandro_pastelaria;

CREATE TABLE Funcionario (
	id_funcionario INT AUTO_INCREMENT,
    nome_completo VARCHAR(100) NOT NULL,
    senha VARCHAR(70) NOT NULL,
    cpf BIGINT(11) NOT NULL,
    rg VARCHAR(24) NOT NULL,
    ctps_num INT(7) NOT NULL,
    ctps_serie INT(3) NOT NULL,
    data_nascimento DATE NOT NULL,
    filiacao VARCHAR(72) NOT NULL,
   	cargo enum('Garçom', 'Cozinheiro', 'Gerente de Estoque', 'Administrador'),
    jornada_semanal INT(2) NOT NULL,
    remuneracao DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_funcionario)
);

SELECT * FROM Funcionario;

INSERT INTO Funcionario(nome_completo, senha, cpf, rg, ctps_num, ctps_serie, data_nascimento, filiacao, cargo, jornada_semanal, remuneracao)
  VALUES ('Murillo Isidoro', 'senha@12', 85926405923, 'MG-84.938.343', 8373894, 342, '1994-06-29', 'Ronaldo Garcia e Rosemeire Isodoro', 'Gerente de Estoque', 20, 2054.05),
  ('Gabriela Barbosa Paiva', 'qwe@123', 87468249351, 'MG-16.768.313', 7483159, 321, '1995-07-30', 'Maria das Graças Paiva e Rone Barbosa', 'Garçom', 35, 1243.45),
  ('Leticia Maria Campos', '12345@31', 58726943510, 'MG-12.774.010', 4853517, 847, '1998-01-12', 'Isabela Campos e Anderson Melo', 'Cozinheiro', 40, 1950.86),
  ('Ricardo Soares Amorim', 'mae@321', 043824957312, 'MG-71.451.113', 48486235, 154, '1993-03-19', 'Eduardo Amorim e Ana Maria Soaras', 'Garçom', 35, 1243.45),
  ('Alexandre Melo', 'pastel@6', 24153704983, 'MG-75.020.816', 1714853, 349, '1980-08-04', 'Elian Melo e Maria Rosa', 'Garçom', 35, 1243.45),
  ('Eduardo Pires Silva', 'edu@1020', 02048535213, 'MG-84.727.754', 0486315, 735, '1975-12-05', 'Mariana Soares Silva e Carlos Pires', 'Administrador', 25, 2146.98),
  ('Leonardo Souza Junior', '183@729', 86865429241, 'MG-51.482.874', 8512152, 403, '1989-10-29', 'Maria Eduarda Souza e Leonardo Paiva', 'Cozinheiro', 40, 1850.86),
  ('Bruna Duarte Souza', '123456##', 10204153780, 'MG-18.558.345', 1515895, 204, '1990-09-25', 'Hilda Duarte e Gilberto Souza', 'Cozinheiro', 40, 1243.45),
  ('Elienai Henrique da Silva', 'Senh@123', 03134284673, 'MG-75.716.756', 9587535, 854, '1985-08-19', 'Daiane da Silva e Guilherme Henrique', 'Garçom', 35, 1243.45),
  ('João Pedro da Cunha', 'PassWd@N', 24251768301, 'MG-44.515.852', 9574020, 843, '1988-11-07', 'Edilamar Ribeiro e Joao Paulo Cunha', 'Garçom', 35, 1243.45),
  ('Isabela Leticia Carneiro', 'n0x1234@', 13148424159, 'MG-94.550.452', 8484532, 509, '1976-05-11', 'Manoela Chris Carneiro e Igor Souza Junior', 'Cozinheiro', 40, 1850.86);

create table Produto(
	nome_produto varchar(100) NOT NULL,
    quantidade int(3),
    tipo_produto enum('Matéria prima', 'Comida', 'Doce', 'Bebida', 'Pastel'),
    id_produto int(8) NOT NULL AUTO_INCREMENT,
    primary key(id_produto)
);

insert into produto(nome_produto, quantidade, tipo_produto) 
	values ("Coca-cola 1,5l", 39, "Bebida"),
    ("Coca-cola 250ml", 53, "Bebida"),
    ("Mineiro 2l", 19, "Bebida"),
    ("Mineiro 250ml", 48, "Bebida"),
    ("Fanta Uva 2l", 29, "Bebida");
insert into produto(nome_produto, tipo_produto)
	values ("Pastel Misto", "Pastel"),
    ("Pastel de Queijo e Presunto", "Pastel"),
    ("Pastel de Pizza", "Pastel"),
	("Pastel de Frango e Catupiry", "Pastel"),
    ("Pastel de Palmito e queijo", "Pastel");
    
select * from produto;

create table mesa(
    id_mesa int AUTO_INCREMENT, 
    limpa boolean NOT NULL, 
    livre boolean NOT NULL, 
    primary key(id_mesa)
);
insert into mesa(limpa, livre) 
	values (TRUE, TRUE),
    (TRUE, FALSE),
    (TRUE, TRUE),
    (FALSE, FALSE),
    (TRUE, TRUE),
    (FALSE, TRUE),
    (TRUE, FALSE),
    (TRUE, TRUE),
	(FALSE, TRUE),
	(FALSE, FALSE),
	(TRUE, TRUE),
	(FALSE, TRUE),
	(TRUE, FALSE),
	(TRUE, FALSE),
	(FALSE, TRUE);
    
select * from mesa;


create table pedido (
    id_pedido int AUTO_INCREMENT, 
	estado_pedido enum('ABERTO', 'FECHADO'),
    estado_cozinha enum('PREPARANDO', 'FINALIZADO'),
    id_mesa int, 
    constraint fk_idMesa foreign key (id_mesa) references mesa(id_mesa),
    primary key(id_pedido)
);
select * from pedido;

insert into pedido(estado, id_mesa)
	values ('AGUARDANDO', 1),
    ('PREPARANDO', 11),
    ('AGUARDANDO', 9),
    ('FINALIZADO', 5),
    ('FINALIZADO', 4);

insert into pedido(estado)
	values ('FINALIZADO'),
    ('PREPARANDO'),
    ('FINALIZADO'),
    ('FINALIZADO'),
    ('AGUARDANDO'),
    ('PREPARANDO');
    
select * from pedido;

CREATE TABLE item_pedido (
	id_pedido INT NOT NULL ,
	id_produto INT NOT NULL,
    quantidade INT NOT NULL,
	constraint fk_idPedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    constraint fk_idProduto FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

INSERT INTO item_pedido(id_pedido, id_produto, quantidade) VALUES
/* Dentro do pedido 1, 2 pasteis de misto */
(1, 6, 2),
/* Dentro do pedido 1, 2 Coca-cola de 250ml */
(1, 2, 5),
/* Dentro do pedido 1, 3 pasteis de pizza */
(1, 8, 3),
/* Dentro do pedido 2, 10 pasteis de queijo e presunto */
(2, 7, 5),
/* Dentro do pedido 2, 2 Coca-cola de 1,5l */
(2, 1, 4),
/* Dentro do pedido 2, 3 Mineiro 250ml */
(2, 4, 3),
/* Dentro do pedido 3, 1 pastel de palmito e queijo */
(3, 10, 1);
select * from item_pedido;
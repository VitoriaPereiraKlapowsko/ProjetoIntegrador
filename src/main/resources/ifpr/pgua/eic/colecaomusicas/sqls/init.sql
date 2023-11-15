CREATE TABLE IF NOT EXISTS tb_funcionario (
login varchar(150) primary key,
senha varchar(45) not null,
nome varchar(250) not null,
sobrenome varchar(250) not null,
telefone int not null,
funcao varchar(250) not null,
cpf varchar(70) not null,
sexo varchar(70) not null,
endereco varchar(450) not null,
data_de_nascimento date not null,
email varchar(250) not null
);

CREATE TABLE IF NOT EXISTS tb_cliente (
codigo varchar(100) primary key,
nome varchar(250) not null,
sobrenome varchar(250) not null,
cpf_cnpj varchar(80) not null,
inscricao_estadual varchar(80),
endereco varchar(450) not null,
telefone int not null,
email varchar(75) not null
);

CREATE TABLE IF NOT EXISTS tb_telefone_cliente (
numero int primary key,
cliente_codigo varchar(100),
ddd int not null,
FOREIGN KEY (cliente_codigo) REFERENCES tb_cliente(codigo)
);

CREATE TABLE IF NOT EXISTS tb_raca (
codigo varchar(45) primary key,
nome varchar(45) not null,
descricao varchar(450)
);

CREATE TABLE IF NOT EXISTS tb_animal (
codigo varchar(45) primary key,
cliente_codigo varchar(100) not null,
raca_codigo varchar(45) not null,
nome varchar(250) not null,
nome_raca varchar(250) not null,
sexo varchar(250) not null,
porte varchar(250) not null,
especie varchar(250) not null,
data_de_nascimento date not null,
tratamento_especiais varchar(400),
condicoes_fisicas varchar(400),
FOREIGN KEY(cliente_codigo) REFERENCES tb_cliente(codigo),
FOREIGN KEY(raca_codigo) REFERENCES tb_raca(codigo)
);

CREATE TABLE IF NOT EXISTS tb_servico (
codigo_do_servico VARCHAR(50) primary key,
valor float not null,
descricao varchar (400) not null
);

CREATE TABLE IF NOT EXISTS tb_agendamento (
codigo varchar(45) primary key,
cliente_codigo varchar(100) not null,
animal_codigo varchar(100) not null,
tipo_servico varchar(100) not null,
funcionario_login varchar(150) not null,
data_reserva_de_servico date not null,
status varchar(250) not null,
horario_do_servico time not null,
valor_total_da_reserva float not null,
tosador_ou_banhista varchar(80) not null,
observacao varchar(250),
FOREIGN KEY(cliente_codigo) REFERENCES tb_cliente(codigo),
FOREIGN KEY(animal_codigo) REFERENCES tb_animal(codigo),
FOREIGN KEY(funcionario_login) REFeRENCES tb_funcionario(login),
FOREIGN KEY(tipo_servico) REFeRENCES tb_servico(codigo_do_servico)
);

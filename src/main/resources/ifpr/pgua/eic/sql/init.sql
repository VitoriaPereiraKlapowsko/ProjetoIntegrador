CREATE TABLE IF NOT EXISTS tb_usuarios (
codigo int primary key AUTO_INCREMENT,
usuario varchar(80) not null,
senha varchar(80) not null
);

CREATE TABLE IF NOT EXISTS tb_funcionario (
codigo int primary key AUTO_INCREMENT,
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
codigo int NOT NULL AUTO_INCREMENT primary key,
nome varchar(250) not null,
sobrenome varchar(250) not null,
cpf_cnpj varchar(80) not null,
inscricao_estadual varchar(80) not null,
endereco varchar(450) not null,
telefone int not null,
email varchar(75) not null
);

CREATE TABLE IF NOT EXISTS tb_raca (
codigo int NOT NULL AUTO_INCREMENT primary key,
nome varchar(45) not null,
descricao varchar(450)
);

CREATE TABLE IF NOT EXISTS tb_animal (
    codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_codigo INT NOT NULL,
    raca_codigo INT NOT NULL,
    nome VARCHAR(250) NOT NULL,
    sexo VARCHAR(250) NOT NULL,
    porte VARCHAR(250) NOT NULL,
    especie VARCHAR(250) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    tratamento_especiais VARCHAR(400),
    condicoes_fisicas VARCHAR(400),
    FOREIGN KEY (cliente_codigo) REFERENCES tb_cliente(codigo),
    FOREIGN KEY (raca_codigo) REFERENCES tb_raca(codigo)
);

CREATE TABLE IF NOT EXISTS tb_servico (
codigo_do_servico int NOT NULL AUTO_INCREMENT primary key,
valor float not null,
descricao varchar (40) not null
);

CREATE TABLE IF NOT EXISTS tb_status (
    codigo_status INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_agendamento (
codigo INT AUTO_INCREMENT PRIMARY KEY,
cliente_codigo INT NOT NULL,
animal_codigo INT NOT NULL,
tipo_servico INT NOT NULL,
codigo_status  INT NOT NULL,
data_reserva_de_servico date not null,
horario_do_servico time not null,
valor_total_da_reserva float not null,
tosador_ou_banhista varchar(80) not null,
observacao varchar(250),
FOREIGN KEY(cliente_codigo) REFERENCES tb_cliente(codigo),
FOREIGN KEY(animal_codigo) REFERENCES tb_animal(codigo),
FOREIGN KEY(tipo_servico) REFERENCES tb_servico(codigo_do_servico)
);

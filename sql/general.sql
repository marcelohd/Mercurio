-- CRIANDO DATABASE
CREATE DATABASE health;
COMMENT ON DATABASE health IS 'Saúde Database';

-- CRIANDO SEQUENCE DE PAISES 
CREATE SEQUENCE country_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE country_sequence  IS 'Sequencia dos Países';

-- CRIANDO TABELAS DE PAISES
CREATE TABLE country(
	code VARCHAR(3) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('country_sequence')::VARCHAR(3),3,'0'),
	name VARCHAR(255) NOT NULL UNIQUE,
	codeI18n VARCHAR(3) NOT NULL UNIQUE,
	acronym VARCHAR(3) NOT NULL, 
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CHECK( code >= '0')
);
COMMENT ON TABLE country  IS 'Tabela dos países';

-- CRIANDO SEQUENCE DE ESTADOS
CREATE SEQUENCE state_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE state_sequence  IS 'Sequencia dos Estados';

-- CRIANDO TABELA DE ESTADOS
CREATE TABLE state (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('state_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL UNIQUE,
	acronym VARCHAR(2) NOT NULL, 
	country VARCHAR (3) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',	
	CONSTRAINT state_x_country FOREIGN KEY (country) REFERENCES country (code),	
	CHECK( code >= '0')
);
COMMENT ON TABLE state  IS 'Tabela dos Estados';

-- CRIANDO SEQUENCE DE CIDADES
CREATE SEQUENCE city_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99999999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE city_sequence  IS 'Sequencia das Cidades';

-- CRIANDO TABELA DE CIDADES
CREATE TABLE city (
	code VARCHAR(8) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('city_sequence')::VARCHAR(8),8,'0'),
	name VARCHAR(255) NOT NULL,
	ibge NUMERIC(8) NOT NULL,
	state VARCHAR(2) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CHECK ( code >= '0'),
	CONSTRAINT city_x_state FOREIGN KEY (state) REFERENCES state (code)	
);
COMMENT ON TABLE city  IS 'Tabela dos Cidades';

-- CRIANDO SEQUENCE DE BAIRROS
CREATE SEQUENCE neighborhood_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99999999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE neighborhood_sequence  IS 'Sequencia dos Bairros';

-- CRIANDO TABELA DE BAIRROS 
CREATE TABLE neighborhood (
	code VARCHAR(8) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('neighborhood_sequence')::VARCHAR(8),8,'0'),
	name VARCHAR(255) NOT NULL,
	city VARCHAR(8) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CHECK ( code >='0' ),
	CONSTRAINT neighborhood_x_city FOREIGN KEY (city) REFERENCES city (code)	
);
COMMENT ON TABLE neighborhood   IS 'Tabela dos Bairros';

-- CRIANDO SEQUENCE DE TIPOS DE RUAS 
CREATE SEQUENCE type_street_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE type_street_sequence  IS 'Sequencia para os tipos de ruas, avenidas';

-- CRIANDO TABELA DE TIPOS DE RUAS 
CREATE TABLE type_street (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('type_street_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL,	
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CHECK ( code >= '0')
);
COMMENT ON TABLE type_street IS 'Tabela dos Tipos de Rua,Avenida etc';

-- CRIANDO SEQUENCE DAS RUAS, ESTRADAS, RODOVIAS
CREATE SEQUENCE street_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE street_sequence  IS 'Sequencia para Ruas, Avenias etc';

-- CRIANDO TABELA DE RUAS
CREATE TABLE street (
	code VARCHAR (10)NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('street_sequence')::VARCHAR(10),10,'0'),
	name VARCHAR(255) NOT NULL,
	postal_code VARCHAR(8) ,
	neighborhood VARCHAR(8) NOT NULL,
	type_street VARCHAR(2) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CONSTRAINT street_x_neighborhood FOREIGN KEY (neighborhood) REFERENCES neighborhood (code),
	CONSTRAINT street_x_type_street FOREIGN KEY (type_street) REFERENCES type_street (code),	
	CHECK ( code >= '0' )
	
);
COMMENT ON TABLE street IS 'Tabela das Ruas';

-- CRIANDO SEQUENCE RAÇA
CREATE SEQUENCE race_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE race_sequence  IS 'Sequencia das Raças';

-- CRIANDO TABELA DE RAÇA
CREATE TABLE race (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('race_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL UNIQUE,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE', 
	CHECK ( code >= '0')
);
COMMENT ON TABLE race IS 'Tabela das Raças';

-- CRIANDO SEQUENCE DO NIVEL ESCOLAR 
CREATE SEQUENCE scholarity_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE scholarity_sequence  IS 'Sequencia das Escolaridade';

-- CRIANDO TABELA DE NIVEL ESCOLAR
CREATE TABLE scholarity (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('scholarity_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL UNIQUE,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE', 
	CHECK ( code >= '0')
);
COMMENT ON TABLE scholarity IS 'Tabela das Nivel Aprendizado';

-- CRIANDO SEQUENCE ESTADO CIVIL
CREATE SEQUENCE marital_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE marital_sequence  IS 'Sequencia do Estado Civil ';

-- CRIANDO TABELA DE ESTADO CIVIL 
CREATE TABLE marital (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('marital_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL UNIQUE,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE', 
	CHECK ( code >= '0')
);
COMMENT ON TABLE marital IS 'Tabela das Estado Civil';

-- CRIANDO SEQUENCE DE EMPRESAS
CREATE SEQUENCE enterprise_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE enterprise_sequence  IS 'Sequencia das empresas';

-- CRIANDO TABELA DE EMPRESAS
CREATE TABLE enterprise (
	code VARCHAR(3) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('enterprise_sequence')::VARCHAR(3),3,'0'),
	corporate_name VARCHAR(255) NOT NULL,
	fantasy VARCHAR(255),	
	street VARCHAR(10) NOT NULL,
	phone VARCHAR(20),
	mail VARCHAR(45),
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CONSTRAINT enterprise_x_street FOREIGN KEY (street) REFERENCES street (code),
	CHECK ( code >= '0')
);
COMMENT ON TABLE enterprise IS 'Tabela da Empresas';
COMMENT ON COLUMN enterprise.corporate_name IS 'Razão Social';
COMMENT ON COLUMN enterprise.fantasy IS 'Nome Fantasia';

-- CRIANDO SEQUENCE DE PESSOAS
CREATE SEQUENCE people_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE people_sequence  IS 'Sequencia das pessoas';

-- CRIANDO TABELA DE PESSOAS
CREATE TABLE people (
	enterprise VARCHAR(3) NOT NULL,
	code VARCHAR(10) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('people_sequence')::VARCHAR(10),10,'0'),
	name VARCHAR(255) NOT NULL,
	mother VARCHAR(255),
	father VARCHAR(255),
	cpf VARCHAR(15) UNIQUE,
	rg VARCHAR(10) UNIQUE,
	cardsus VARCHAR(20) UNIQUE NOT NULL,
	scholarity VARCHAR(2) NOT NULL,
	race  VARCHAR(2) NOT NULL,
	marital  VARCHAR(2) NOT NULL,	 
	databirth  TIMESTAMP NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CONSTRAINT people_x_enterprise FOREIGN KEY (enterprise) REFERENCES enterprise (code),
	CONSTRAINT people_x_marital FOREIGN KEY (marital) REFERENCES marital (code),
	CONSTRAINT people_x_race FOREIGN KEY (race) REFERENCES race (code),
	CONSTRAINT people_x_scholarity FOREIGN KEY (scholarity) REFERENCES scholarity (code),
	CHECK ( code >= '0')
);
COMMENT ON TABLE people IS 'Tabela da pessoas';

-- CRIANDO SEQUENCE DE TIPOS DE FUNCIONARIOS
CREATE SEQUENCE type_employee_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE type_employee_sequence  IS 'Sequencia dos tipos de funcionarios';

-- CRIANDO TABELA DE TIPOS DE FUNCIONARIOS
CREATE TABLE type_employee (
	code VARCHAR(2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('type_employee_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CHECK ( code >= '0')
);
COMMENT ON TABLE type_employee IS 'Tabela dos Tipos de Funcionarios, Cargos';

-- CRIANDO SEQUENCE DE FUNCIONARIOS 
CREATE SEQUENCE employee_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE employee_sequence  IS 'Sequencia dos Funcionarios';

-- CRIANDO TABELA DE FUNCIONARIOS
CREATE TABLE employee (
	code VARCHAR(4) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('employee_sequence')::VARCHAR(4),4,'0'),
	enterprise VARCHAR(3) NOT NULL,	
	people VARCHAR(10) NOT NULL,
	register VARCHAR (15) NOT NULL,
	type_employe VARCHAR(2) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CONSTRAINT employee_x_enterprise FOREIGN KEY (enterprise) REFERENCES enterprise (code),
	CONSTRAINT employee_x_people FOREIGN KEY (people) REFERENCES people (code),
	CONSTRAINT employee_x_type_employe FOREIGN KEY (type_employe) REFERENCES type_employee (code),
	CHECK ( code >= '0')
);
COMMENT ON TABLE employee IS 'Tabela dos Funcionarios, Cargos';
COMMENT ON COLUMN employee.register IS 'Numero de Registro do Profissional';
COMMENT ON COLUMN employee.type_employe IS 'Tipo de profissional';

-- CRIANDO SEQUENCE DE ENDEREÇOS
CREATE SEQUENCE address_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999999 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE address_sequence  IS 'Sequencia dos Funcionarios';

-- CRIANDO TABELA DE ENDEREÇOS
CREATE TABLE address (
	code VARCHAR(10) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('address_sequence')::VARCHAR(10),10,'0'),
	enterprise VARCHAR(3) NOT NULL,	
	people VARCHAR(10) NOT NULL, 
	postal_code VARCHAR(8) NOT NULL,
	number_place VARCHAR(6) DEFAULT 'S/N',
	complement VARCHAR(255),
	street VARCHAR(10) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',
	CONSTRAINT address_x_enterprise FOREIGN KEY (enterprise) REFERENCES enterprise (code),
	CONSTRAINT address_x_people FOREIGN KEY (people) REFERENCES people (code),
	CONSTRAINT address_x_street FOREIGN KEY (street) REFERENCES street (code),
	CHECK ( code >= '0')
);
COMMENT ON TABLE address IS 'Tabela dos Endereços';
COMMENT ON COLUMN address.complement IS 'Tipo de complement';
COMMENT ON COLUMN address.number_place IS 'Numero do Local';
COMMENT ON COLUMN address.postal_code IS 'CEP';

-- CRIANDO SEQUENCE DAS CONVENIOS
CREATE SEQUENCE agreement_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE agreement_sequence  IS 'Sequencia para Convenios ';

-- CRIANDO TABELA DE CONVENIOS
CREATE TABLE agreement(
	code VARCHAR (2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('agreement_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT '0',	
	CHECK ( code >='0')		
);
COMMENT ON TABLE agreement IS 'Tabela das Convenios';

-- CRIANDO SEQUENCE DAS STATUS
CREATE SEQUENCE status_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE status_sequence  IS 'Sequencia para Convenios ';

-- CRIANDO TABELA DE STATUS
CREATE TABLE status(
	code VARCHAR (2) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('status_sequence')::VARCHAR(2),2,'0'),
	name VARCHAR(255) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT '0',	
	CHECK ( code >='0')		
);
COMMENT ON TABLE status  IS 'Tabela dos status';

-- CRIANDO SEQUENCE DE TELEFONE E E-MAIL 
CREATE SEQUENCE contacts_sequence INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 99 CACHE 1 NO CYCLE;
COMMENT ON SEQUENCE contacts_sequence  IS 'Sequencia dos Contato';

-- CRIANDO TABELA DE CONTATO (TELEFONE E MAIL)
CREATE TABLE contacts (
	code VARCHAR(4) NOT NULL PRIMARY KEY UNIQUE DEFAULT lpad(nextval('contacts_sequence')::VARCHAR(4),4,'0'),
	enterprise VARCHAR(3) NOT NULL,	
	people VARCHAR(10) NOT NULL,
	email_p	VARCHAR(255) NOT NULL,
	email_a	VARCHAR(255),
	phone VARCHAR(255) NOT NULL,
	cellphone VARCHAR(255) NOT NULL,
	trash BOOLEAN NOT NULL DEFAULT 'FALSE',  
	CONSTRAINT contacts_x_enterprise FOREIGN KEY (enterprise) REFERENCES enterprise (code),
	CONSTRAINT contacts_x_people FOREIGN KEY (people) REFERENCES people (code), -- INSERINDO AO TERMINAR SQL
	CHECK ( code >= '0')
);
COMMENT ON TABLE contacts IS 'Tabela da Contato';

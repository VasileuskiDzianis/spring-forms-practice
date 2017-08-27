DROP TABLE IF EXISTS user;
CREATE TABLE user (
	userId INT NOT NULL AUTO_INCREMENT,
	login VARCHAR(90) NOT NULL,
	password VARCHAR(100) NOT NULL,
	address INT NOT NULL,
	age INT NOT NULL,
	locale VARCHAR(3),
	status VARCHAR(15),
	KEY addressId (address),
	PRIMARY KEY (userId)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS address;
CREATE TABLE address (
	addressId INT NOT NULL AUTO_INCREMENT,
	country VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	PRIMARY KEY (addressId)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS skill;
CREATE TABLE skill (
	skillId INT NOT NULL AUTO_INCREMENT,
	skillName VARCHAR(100) NOT NULL,
	PRIMARY KEY (skillId)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS user_skill;
CREATE TABLE user_skill (
	user INT NOT NULL,
	skill INT NOT NULL,
	KEY skillId (skill),
	KEY userId (user)
)ENGINE=InnoDB CHARSET=utf8;

LOCK TABLES user WRITE, address WRITE, skill WRITE, user_skill WRITE;

INSERT INTO user VALUES 
	('1', 'Shield', 'qwerty', '1', '54', 'en', 'ACTIVE'), 
	('2', 'Ekkel', 'password', '2', '60', 'fr', 'ACTIVE'),
	('3', 'Horstmann', 'goodbook', '3', '55', 'en', 'DISABLED');

INSERT INTO address VALUES 
	('1', 'United States', 'Dallas'), 
	('2', 'France', 'Paris'), 
	('3', 'United States', 'Chicago');
	
INSERT INTO skill VALUES 
	('1', 'Java'), 
	('2', 'Python'), 
	('3', 'SQL'), 
	('4', 'C++'), 
	('5', 'Spring'), 
	('6', 'Hibernate'), 
	('7', 'JavaScript');

INSERT INTO user_skill VALUES 
	('1','1'), ('1','2'), ('1', '3'), 
	('2','2'), ('2','3'), ('2', '4'), 
	('3','1');

UNLOCK TABLES;
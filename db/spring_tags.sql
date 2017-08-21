DROP TABLE IF EXISTS user;
CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	login VARCHAR(90) NOT NULL,
	password VARCHAR(100) NOT NULL,
	address INT NOT NULL,
	locale VARCHAR(3),
	KEY address_id (address),
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS address;
CREATE TABLE address (
	id INT NOT NULL AUTO_INCREMENT,
	country VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS skill;
CREATE TABLE skill (
	id INT NOT NULL AUTO_INCREMENT,
	skillName VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS user_skill;
CREATE TABLE user_skill (
	user INT NOT NULL,
	skill INT NOT NULL,
	KEY skillId (skill),
	KEY userId (user)
)ENGINE=InnoDB CHARSET=utf8;

LOCK TABLES user WRITE, address WRITE, skill WRITE, user_skill WRITE;

INSERT INTO user VALUES ('1', 'Shield', 'qwerty', '1', 'en'), ('2', 'Ekkel', 'password', '2', 'fr');
INSERT INTO address VALUES ('1', 'United States', 'Dallas'), ('2', 'France', 'Paris');
INSERT INTO skill VALUES ('1', 'Java'), ('2', 'Python'), ('3', 'SQL'), ('4', 'C++'), ('5', 'Spring'), ('6', 'Hibernate');
INSERT INTO user_skill VALUES ('1','1'), ('1','2'), ('1', '3'), ('2','2'), ('2','3'), ('2', '4'); 

UNLOCK TABLES;
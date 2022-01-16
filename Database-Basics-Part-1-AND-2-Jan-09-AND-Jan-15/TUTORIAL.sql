-- CREATING A DATABASE
CREATE DATABASE office_management;

-- CREATING A DATABASE ONLY IF IT DOES NOT EXIST
CREATE DATABASE IF NOT EXISTS office_management;

-- USING THE DATABASE JUST CREATED
USE office_management;

-- CHECKING THE CURRENT DATABASE;
SELECT DATABASE();

-- SHOWING ALL THE DATABASES
SHOW DATABASES;

-- CREATING AN EMPLOYEE TABLE
CREATE TABLE employee(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	f_name VARCHAR(100) NOT NULL,
	l_name VARCHAR(100) DEFAULT NULL,
	id_type ENUM("AADHAAR", "VOTER_ID") NOT NULL,
	id_num VARCHAR(50) NOT NULL,
	CONSTRAINT uk_id_type_and_num UNIQUE(id_type, id_num)
);

-- CREATING AN EMPLOYEE DEPENDENT TABLE
CREATE TABLE employee_dependent (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	employee_id BIGINT NOT NULL,
	f_name VARCHAR(100) NOT NULL,
	l_name VARCHAR(100) NOT NULL,
	gender ENUM("M", "F", "O") NOT NULL
	id_type ENUM("AADHAAR", "VOTER_ID") NOT NULL,
	id_num VARCHAR(50) NOT NULL,
	relationship ENUM("PARENT", "SIBLING", "CHILD") NOT NULL
	CONSTRAINT fk_employee_id_employee_dependent FOREIGN KEY(employee_id) REFERENCES employee(id) CASCADE ON UPDATE CASCADE ON DELETE,
	CONSTRAINT uk_id_type_and_num_employee_dependent UNIQUE(id_type, id_num)
)

-- SHOW ALL TABLES
SHOW TABLES;

-- DESCRIBE SCHEMA OF employee TABLE
SHOW CREATE TABLE employee;

-- DESCRIBE SCHEMA OF employee_dependent TABLE
SHOW CREATE TABLE employee_dependent;

-- INSERT VALUE INTO employee TABLE
INSERT INTO 
	employee(f_name, l_name, id_type, id_num) 
VALUE 
	("John", "Morris", "AADHAAR", "AD1234"),
	("Johnny", "Dave", "AADHAAR", "AD1235"),
	("Jonas", "Jlo", "AADHAAR", "AD1236"),
	("Arpit", "Mishra", "VOTER_ID", "AD1237"),
	("Arjun", "Rao", "VOTER_ID", "AD1238")

-- INSERT VALUE INTO employee_dependent TABLE
INSERT INTO 
	employee_dependent(employee_id, f_name, l_name, gender, id_type, id_num, relationship) 
VALUES 
	(1, "Papa", "Morris", "M", "AADHAAR", "ED1234", "PARENT"),
	(1, "Momma", "Morris", "F", "VOTER_ID", "ED1234", "PARENT"),
	(1, "Sissy", "Morris", "F", "AADHAAR", "ED1235", "SIBLING");

-- SHOW ALL ROWS FROM employee TABLE
SELECT * FROM employee;

-- SHOW ALL ROWS FROM employee_dependent TABLE
SELECT * FROM employee_dependent;

-- UPDATING A RECORD IN employee TABLE WHERE id_type is AADHAAR and id_num is given
UPDATE employee SET f_name = "Johnny", l_name = "Milton" WHERE id_type = 'AADHAAR' AND id_num = "AD1234";

-- UPDATING THE l_name OF ALL THE EMPLOYEES HAVING THEIR f_name BEGINS WITH 'Jo' OR HAS 'ar' IN BETWEEN 
UPDATE employee SET l_name = "Kumar" WHERE f_name LIKE "Jo%" OR f_name LIKE "%ar%";

-- FIND ALL EMPLOYEES HAVING BOTH AADHAAR AND VOTER_ID
SELECT * FROM employee e WHERE e.id_type IN ("AADHAAR", "VOTER_ID");

-- SELECT ALL THE EMPLOYEES WHO HAVE FEMALE DEPENDENTS
SELECT * FROM employee e WHERE e.id IN (SELECT ed.id FROM employee_dependent ed WHERE ed.gender = "F");

-- SELECT ALL THE FEMALE EMPLOYEE DEPENDENTS OF EMPLOYEES THAT HAVE AADHAAR
SELECT * FROM employee_dependent ed WHERE ed.gender = "F" AND ed.id IN (SELECT e.id FROM employee e);

-- DELETE ALL THE EMPLOYEE DEPENDENTS WHO HAVE AADHAAR AS THEIR id_type
DELETE FROM employee_dependent WHERE id_type = "AADHAAR";

-- INNER JOIN BETWEEN employee AND employee_dependent
SELECT * FROM employee e INNER JOIN employee_dependent ed ON e.id = ed.employee_id;

-- LEFT JOIN BETWEEN employee AND employee_dependent
SELECT * FROM employee e LEFT JOIN employee_dependent ed ON e.id = ed.employee_id;

-- RIGHT JOIN BETWEEN employee AND employee_dependent
SELECT * FROM employee e RIGHT JOIN employee_dependent ed ON e.id = ed.employee_id;









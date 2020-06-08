CREATE TABLE user (id BIGINT AUTO_INCREMENT NOT NULL, birth_date DATE, created_by VARCHAR(255), created_on DATETIME(3), deleted TINYINT(1) default 0, fname VARCHAR(255) NOT NULL, lname VARCHAR(255) NOT NULL, modified_by VARCHAR(255), modified_on DATETIME(3), pwd VARCHAR(255) NOT NULL, usr VARCHAR(255) NOT NULL, version BIGINT, PRIMARY KEY (id))


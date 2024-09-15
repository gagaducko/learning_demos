CREATE DATABASE mssystem;
USE mssystem;
CREATE TABLE dict_item
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    code    VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL
);

CREATE DATABASE libraryDB;
USE libraryDB;
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    available_date DATE NULL );

CREATE TABLE workers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL ,
    password  VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL);

CREATE TABLE admin (id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL );

INSERT INTO admin (username, password) VALUES ('admin', '0000');




CREATE TABLE visitors (
                          visitor_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255),
                          last_name VARCHAR(255),
                          login VARCHAR(255) UNIQUE,
                          password VARCHAR(255),
                          membership_expiry_date DATE,
                          membership_active boolean

);




CREATE TABLE visitors (
                          visitor_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255),
                          last_name VARCHAR(255),
                          login VARCHAR(255) UNIQUE,
                          password VARCHAR(255)
);

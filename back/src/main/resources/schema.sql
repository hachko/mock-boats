CREATE TABLE users (
  id          INT PRIMARY KEY auto_increment,
  username    VARCHAR(20) NOT NULL,
  password    VARCHAR(20) 
);

CREATE TABLE boats (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(20) NOT NULL,
  description VARCHAR(64) 
);
CREATE TABLE users (
  id          INT PRIMARY KEY auto_increment,
  username    VARCHAR(20) NOT NULL,
  password    VARCHAR(200) NOT NULL,
  UNIQUE (username)
);

CREATE TABLE roles (
  name VARCHAR(20) PRIMARY KEY
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_name) REFERENCES roles(name)
);


CREATE TABLE boats (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(20) NOT NULL,
  description VARCHAR(300), 
  imageUrl VARCHAR(300)
);
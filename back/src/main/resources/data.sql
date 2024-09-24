INSERT INTO boats (name, description) VALUES ('first', 'first boat');
INSERT INTO boats (name, description) VALUES ('second', 'second boat');
INSERT INTO boats (name, description) VALUES ('third',  'third boat');
INSERT INTO boats (name, description) VALUES ('fourth', 'fourth boat');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users (username, password) VALUES ('admin', '$2a$10$3vfmMLAYnO9tqDeyM.OlwOeq8wM0nxGpG9XBv0.pd5HWDtjPbBDZS');
INSERT INTO users (username, password) VALUES ('user','$2a$10$BF2CzRG6rlcuXKNO5wvmlOvqskYZA3jAPLAOOegYzFHzO4yk.yAVG');

INSERT INTO user_role (user_id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO user_role (user_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_role (user_id, role_name) VALUES (2, 'ROLE_USER');
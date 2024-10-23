INSERT INTO boats (name, description, imageUrl) VALUES ('Hermione', 'One of the most iconic boats of the 18th century''s French Marine', 'https://static.actu.fr/uploads/2018/10/3599252ee3de999fb973d53f8f9bd8bf_XL-960x599.jpg');
INSERT INTO boats (name, description, imageUrl) VALUES ('Tahiti Dream', 'The skippers''s dream. Can lead you anywhere in the most efficient way', 'https://media.admagazine.fr/photos/646dd8f5d014fe52cb428835/16:9/w_1920,c_limit/Couv''.jpg');
INSERT INTO boats (name, description, imageUrl) VALUES ('Biggy Small',  'This one is pretty big and will cost you in various fees, but offers the most state of the art comfort', 'https://www.masculin.com/wp-content/uploads/sites/2/article/11588/5-plus-beaux-bateaux-yachting-cannes-2015.jpg');
INSERT INTO boats (name, description, imageUrl) VALUES ('Tricky', 'Well, if you felle adventurous, you can still giv it a try', 'https://remeng.rosselcdn.net/sites/default/files/dpistyles_v2/rem_16_9_1124w/2023/03/21/node_465524/13096563/public/2023/03/21/B9733720999Z.1_20230321200219_000%2BGIJMD3IOC.3-0.jpg?itok=E9iFA4jn1679425348');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users (username, password) VALUES ('admin', '$2a$10$3vfmMLAYnO9tqDeyM.OlwOeq8wM0nxGpG9XBv0.pd5HWDtjPbBDZS');
INSERT INTO users (username, password) VALUES ('user','$2a$10$BF2CzRG6rlcuXKNO5wvmlOvqskYZA3jAPLAOOegYzFHzO4yk.yAVG');

INSERT INTO user_role (user_id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO user_role (user_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_role (user_id, role_name) VALUES (2, 'ROLE_USER');
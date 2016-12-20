CREATE TABLE user (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(200) NOT NULL UNIQUE,
    `email` VARCHAR(200) NOT NULL UNIQUE,
    `first_name` VARCHAR(200) NOT NULL,
    `last_name` VARCHAR(200) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
    );

INSERT INTO user (user_id, email, first_name, last_name, password)
VALUES ("1234", "test", "firstName", "lastName", "test");

CREATE TABLE user_role (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user_role (role)
VALUES ("ADMIN");

INSERT INTO user_role (role)
VALUES ("USER");

CREATE TABLE user_role_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(20) NOT NULL,
    `user_role_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (user_role_id) REFERENCES user_role(id)
);

INSERT INTO user_role_mapping (user_id, user_role_id)
VALUES (1, 1);

INSERT INTO user_role_mapping (user_id, user_role_id)
VALUES (1, 2);


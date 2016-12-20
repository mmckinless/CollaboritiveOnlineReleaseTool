CREATE TABLE requirements (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `requirement_id` VARCHAR(200) NOT NULL UNIQUE,
    `requirement_name` VARCHAR(200) NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    `effort` int(2) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE requirement_user_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `requirement_id` BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    `value` BIGINT(20) NOT NULL,
    `conflict` VARCHAR(200),
    `dependency` VARCHAR(200),
    PRIMARY KEY (id),
    FOREIGN KEY (requirement_id) REFERENCES requirements(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
    );

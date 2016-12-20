CREATE TABLE releases (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `release_id` VARCHAR(200) NOT NULL,
    `release_name` VARCHAR(200) NOT NULL,
    `release_date` VARCHAR(200),
    PRIMARY KEY (id)
    );

CREATE TABLE release_requirement_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `release_id` BIGINT(20) NOT NULL,
    `requirement_id` BIGINT(20) NOT NULL,
    `effort` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (release_id) REFERENCES releases(id),
    FOREIGN KEY (requirement_id) REFERENCES requirements(id)
);

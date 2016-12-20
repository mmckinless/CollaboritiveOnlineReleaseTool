CREATE TABLE company (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `company_id` VARCHAR(200) NOT NULL UNIQUE,
    `company_name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE company_user_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `company_id` BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);


CREATE TABLE company_requirement_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `company_id` BIGINT(20) NOT NULL,
    `requirement_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (requirement_id) REFERENCES requirements(id)
);


CREATE TABLE company_release_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `company_id` BIGINT(20) NOT NULL,
    `release_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (release_id) REFERENCES releases(id)
);
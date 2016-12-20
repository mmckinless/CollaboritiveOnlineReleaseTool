CREATE TABLE project (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `project_id` VARCHAR(200) NOT NULL UNIQUE,
    `project_name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE project_release_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `project_id` BIGINT(20) NOT NULL,
    `release_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (release_id) REFERENCES releases(id)
);

CREATE TABLE company_project_mapping (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `company_id` BIGINT(20) NOT NULL,
    `project_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (project_id) REFERENCES project(id)
);
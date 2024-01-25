CREATE TABLE IF NOT EXISTS users (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     username varchar(50) NOT NULL,
     password varchar(50) NOT NULL,
     email varchar(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    description varchar(150) NOT NULL,
    createdDate DATE NOT NULL
);
CREATE TABLE IF NOT EXISTS project_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    related_entity_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
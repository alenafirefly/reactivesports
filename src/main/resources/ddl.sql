CREATE TABLE IF NOT EXISTS sport (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);
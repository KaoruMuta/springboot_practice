DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
    id INT(20) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
) DEFAULT CHARACTER SET = utf8;
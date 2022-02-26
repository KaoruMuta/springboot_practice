DROP DATABASE IF EXISTS mywork;

CREATE DATABASE mywork;

USE mywork;

DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
    id INT(20) AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
) DEFAULT CHARACTER SET = utf8;

INSERT INTO
    tasks (name)
VALUES
    ("Kotlinの導入検討"),
    ("APIのエンドポイント設計"),
    ("DB設計"),
    ("アクセス負荷対応");
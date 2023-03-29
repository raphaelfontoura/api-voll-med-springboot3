CREATE TABLE usuarios (
    id bigint NOT NULL auto_increment,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);
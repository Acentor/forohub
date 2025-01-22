CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    author VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);
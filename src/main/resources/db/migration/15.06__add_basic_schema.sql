CREATE TABLE authors
(
    id       SERIAL PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL
);

CREATE TABLE books
(
    isbn      VARCHAR(13) PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id INTEGER REFERENCES authors (id)
);

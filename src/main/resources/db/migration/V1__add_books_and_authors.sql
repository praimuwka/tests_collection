CREATE TABLE authors
(
    id        SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE books
(
    id        BIGSERIAL PRIMARY KEY,
    isbn      BIGINT UNIQUE,
    title     VARCHAR(255) NOT NULL,
    author_id INTEGER REFERENCES authors (id)
);

-- Создание таблицы для хранения информации о книгах
CREATE TABLE books
(
    id     SERIAL PRIMARY KEY,
    title  VARCHAR(255)       NOT NULL,
    author VARCHAR(255)       NOT NULL,
    isbn   VARCHAR(13) UNIQUE NOT NULL
);

-- Создание таблицы для хранения информации о клиентах
CREATE TABLE clients
(
    id         SERIAL PRIMARY KEY,
    full_name  VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL
);

-- Создание таблицы для хранения информации о взятии и возвращении книг
CREATE TABLE book_operations
(
    id             SERIAL PRIMARY KEY,
    book_id        INTEGER REFERENCES books (id),
    client_id      INTEGER REFERENCES clients (id),
    operation_date DATE        NOT NULL,
    operation_type VARCHAR(50) NOT NULL -- 'borrowed' или 'returned'
);

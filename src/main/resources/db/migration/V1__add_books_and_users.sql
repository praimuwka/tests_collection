-- Создание таблицы для хранения информации о книгах
CREATE TABLE IF NOT EXISTS books
(
    id     SERIAL PRIMARY KEY,
    isbn   VARCHAR(13)  NOT NULL UNIQUE,
    title  VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

-- Создание таблицы для хранения информации о клиентах
CREATE TABLE IF NOT EXISTS clients
(
    id         SERIAL PRIMARY KEY,
    full_name  VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL
);

-- Создание таблицы для хранения информации о взятии и возвращении книг
CREATE TABLE IF NOT EXISTS book_operations
(
    id            SERIAL PRIMARY KEY,
    book_id       INTEGER REFERENCES books (id),
    client_id     INTEGER REFERENCES clients (id),
    borrowed_date DATE NOT NULL,
    returned_date DATE NULL
);

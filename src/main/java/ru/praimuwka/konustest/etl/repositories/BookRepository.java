package ru.praimuwka.konustest.etl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.praimuwka.konustest.etl.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
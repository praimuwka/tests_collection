package ru.praimuwka.dynamicatest.etl.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.praimuwka.dynamicatest.etl.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();
}
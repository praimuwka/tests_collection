package ru.praimuwka.konustest.etl.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.praimuwka.konustest.etl.models.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
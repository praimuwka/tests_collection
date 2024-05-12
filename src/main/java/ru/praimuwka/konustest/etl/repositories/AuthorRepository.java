package ru.praimuwka.konustest.etl.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.praimuwka.konustest.etl.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
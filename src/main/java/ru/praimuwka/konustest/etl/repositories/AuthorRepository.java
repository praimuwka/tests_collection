package ru.praimuwka.konustest.etl.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.praimuwka.konustest.etl.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAll();
}
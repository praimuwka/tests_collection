package ru.praimuwka.konustest.etl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.praimuwka.konustest.etl.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
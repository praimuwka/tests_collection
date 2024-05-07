package ru.praimuwka.dynamicatest.etl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.praimuwka.dynamicatest.etl.entities.BookOperation;

public interface BookOperationRepository extends JpaRepository<BookOperation, Long> {
}
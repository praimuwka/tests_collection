package ru.praimuwka.dynamicatest.etl.repositories;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

import java.util.*;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import ru.praimuwka.dynamicatest.etl.entities.BookOperation;

public interface BookOperationRepository extends JpaRepository<BookOperation, Long> {

    @Query(value = "SELECT b FROM BookOperation b WHERE b.returnedDate IS NULL")
    List<BookOperation> findUnreturnedBooks();

    @Query(value = "SELECT b FROM BookOperation b WHERE b.returnedDate IS NULL")
    @QueryHints({@QueryHint(name = HINT_FETCH_SIZE, value = "100")})
    List<BookOperation> findUnreturnedBooksWithLimitAndOffset(@Param("limit") int limit, @Param("offset") int offset);
}
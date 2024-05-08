package ru.praimuwka.dynamicatest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import ru.praimuwka.dynamicatest.etl.repositories.BookOperationRepository;
import ru.praimuwka.dynamicatest.rest.models.BorrowedBookRestObject;

@Service
public class BookService {
    @Autowired
    private SimpleDateFormat dateFormat;
    @Autowired
    private BookOperationRepository bookOperationRepository;

    public List<BorrowedBookRestObject> getBorrowedBooks() {
        return bookOperationRepository.findUnreturnedBooks().stream()
            .map(bo -> new BorrowedBookRestObject(
                bo.getClient().getFullName(),
                dateFormat.format(bo.getClient().getBirthDate()),
                bo.getBook().getTitle(),
                bo.getBook().getAuthor(),
                bo.getBook().getIsbn(),
                dateFormat.format(bo.getBorrowedDate())
            ))
            .collect(Collectors.toList());
    }
}

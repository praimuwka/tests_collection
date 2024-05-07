package ru.praimuwka.dynamicatest.rest.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.praimuwka.dynamicatest.etl.entities.Book;
import ru.praimuwka.dynamicatest.rest.models.BorrowedBookRestObject;
import ru.praimuwka.dynamicatest.services.BookService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/getAllBorrowed")
    public ResponseEntity<List<BorrowedBookRestObject>> getAllBooks() {
        return ResponseEntity.ok(bookService.getBorrowedBooks());
    }
}

package ru.praimuwka.dynamicatest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import ru.praimuwka.dynamicatest.etl.entities.Book;
import ru.praimuwka.dynamicatest.etl.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }
}

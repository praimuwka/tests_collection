package ru.praimuwka.konustest.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.praimuwka.konustest.etl.models.Author;
import ru.praimuwka.konustest.etl.models.Book;
import ru.praimuwka.konustest.etl.repositories.AuthorRepository;
import ru.praimuwka.konustest.etl.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(Book book) {
        Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
        if (author.isPresent()) {
            book.setAuthor(author.get());
            return bookRepository.save(book);
        }
        return null;
    }

    public Book updateBook(Long id, Book bookUpd) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return null;
        }
        Book book = optionalBook.get();
        Optional<Author> author;
        if (Objects.nonNull(bookUpd.getAuthor())) {
            author = authorRepository.findById(bookUpd.getAuthor().getId());
            if (author.isEmpty()) {
                return null;
            }
            book.setAuthor(author.get());
        }
        if (Objects.nonNull(bookUpd.getIsbn())) {
            book.setIsbn(bookUpd.getIsbn());
        }
        if (Objects.nonNull(bookUpd.getTitle())) {
            book.setTitle(bookUpd.getTitle());
        }
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // public List<Book> findBooksByTitleOrIsbnOrAuthor(String title, Integer isbn, Author author) {
    //     return bookRepository.findBooksByTitleOrIsbnOrAuthor(title, isbn, author);
    // }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

package ru.praimuwka.konustest.etl.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.praimuwka.konustest.etl.models.Author;
import ru.praimuwka.konustest.etl.models.Book;
import ru.praimuwka.konustest.etl.repositories.AuthorRepository;
import ru.praimuwka.konustest.etl.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
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

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<List<Book>> findBooksByParams(String isbn, String title, Integer authorId) {
        if (Objects.isNull(isbn) && Objects.isNull(title) && Objects.isNull(authorId)){
            return Optional.empty();
        }
        List<String> params = new ArrayList<>();
        addParamIfNotNull("isbn", isbn, params);
        addParamIfNotNull("author_id", authorId, params);
        addParamIfNotNull("title", String.join("", "%", title, "%"), params, "ILIKE");
        String sqlTemplate =
            "SELECT b.id, b.isbn, b.title, b.author_id, a.full_name author_full_name "
            + "FROM books b, authors a "
            + "WHERE b.author_id = a.id AND %s";
        String sql = String.format(sqlTemplate, String.join(" AND ", params));
        return Optional.of(jdbcTemplate.query(sql, Book.getMapper()));
    }

    private List<String> addParamIfNotNull(String paramName, Object paramValue, List<String> params){
        return addParamIfNotNull(paramName, paramValue, params, "=");
    }

    private List<String> addParamIfNotNull(String paramName, Object paramValue, List<String> params, String compareSymbol) {
        if (Objects.nonNull(paramValue)) {
            paramValue = (paramValue instanceof String) ? String.format("'%s'", paramValue) : paramValue.toString();
            params.add(String.format("%s %s %s", paramName, compareSymbol,  paramValue));
        }
        return params;
    }
}

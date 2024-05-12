package ru.praimuwka.konustest.rest.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.praimuwka.konustest.etl.models.Book;
import ru.praimuwka.konustest.etl.services.BookService;
import ru.praimuwka.konustest.rest.dto.BookDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Получить список со всеми известными книгами")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    @Operation(summary = "Добавить новую книгу")
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book savedBook = bookService.saveBook(bookDto.toEntity());
        return Objects.nonNull(savedBook) ? ResponseEntity.ok(savedBook) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Изменить данные автора")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить данные книги",
               description = "Позволяет выборочно или полностью изменять данные книги")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails.toEntity());
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги", responses = {
        @ApiResponse(responseCode = "204", description = "Удалено успешно") })
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @Operation(summary = "Поиск книг по набору параметров",
               description = "Позволяет искать книги по набору параметров. Для поиска необходимо от 1 до 3 параметров. "
                             + "Поиск по параметру \"title\" производится по частичному совпадению с названием книги")
    public ResponseEntity<List<Book>> searchBooks(@Valid @RequestBody BookDto bookDetails) {
        Optional<List<Book>> books = bookService.findBooksByParams(
            bookDetails.getIsbn(), bookDetails.getTitle(), bookDetails.getAuthorId());
        if (books.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(books.get());
    }
}

package ru.praimuwka.konustest.rest.controllers;

import java.util.List;

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
import ru.praimuwka.konustest.etl.models.Author;
import ru.praimuwka.konustest.rest.dto.AuthorDto;
import ru.praimuwka.konustest.etl.services.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public final class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    @Operation(summary = "Получить список со всеми известными авторами")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    @Operation(summary = "Добавить нового автора")
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody AuthorDto author) {
        Author savedAuthor = authorService.saveAuthor(author.toEntity());
        return ResponseEntity.ok(savedAuthor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить данные автора")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.findAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить данные автора")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @Valid @RequestBody AuthorDto authorDetails) {
        Author author = authorService.findAuthorById(id);
        if (author!= null) {
            author.setFullName(authorDetails.getFullName());
            return ResponseEntity.ok(authorService.saveAuthor(author));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление автора", responses = {
        @ApiResponse(responseCode = "204", description = "Удалено успешно") })
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
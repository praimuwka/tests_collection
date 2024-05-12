package ru.praimuwka.konustest.rest.dto;

import java.util.*;

import ru.praimuwka.konustest.etl.models.Author;
import ru.praimuwka.konustest.etl.models.Book;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {
    @Pattern(regexp = "^(\\d{10})(\\d{3})?$")
    private String isbn;
    @Size(max = 255)
    private String title;
    private Integer authorId;

    public Book toEntity() {
        return new Book(
            null,
            (isbn != null) ? Long.valueOf(isbn) : null,
            title,
            authorId != null ? new Author(authorId, null) : null
        );
    }
}

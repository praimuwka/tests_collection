package ru.praimuwka.konustest.rest.dto;

import java.util.*;

import ru.praimuwka.konustest.etl.models.Author;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthorDto {
    @Size(max = 255)
    String fullName;

    public Author toEntity() {
        return new Author(
            null,
            Optional.of(fullName).orElse(null)
        );
    }
}

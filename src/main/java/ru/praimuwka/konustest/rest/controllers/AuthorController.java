package ru.praimuwka.konustest.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.praimuwka.konustest.services.AuthorService;

@RestController
@RequestMapping("/api-v1/authors")
public final class AuthorController {
    // @Autowired
    // private AuthorService authorService;

    @GetMapping("/list")
    public ResponseEntity<String> getAllBooks() {
        return ResponseEntity.ok("works");
    }
}
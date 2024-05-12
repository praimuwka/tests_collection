package ru.praimuwka.konustest.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.praimuwka.konustest.etl.models.Author;
import ru.praimuwka.konustest.etl.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Author findAuthorById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }
    public void deleteAuthorById(Integer id) {
        authorRepository.deleteById(id);
    }
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}

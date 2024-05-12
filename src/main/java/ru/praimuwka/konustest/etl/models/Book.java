package ru.praimuwka.konustest.etl.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)")
    private Integer isbn;
    @Size(max = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}

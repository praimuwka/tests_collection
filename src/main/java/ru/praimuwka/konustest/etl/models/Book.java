package ru.praimuwka.konustest.etl.models;

import org.springframework.jdbc.core.RowMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long isbn;
    @Size(max = 255)
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public static RowMapper<Book> getMapper() {
        return (rs, rowNum) -> new Book(
            rs.getLong("id"),
            rs.getLong("isbn"),
            rs.getString("title"),
            new Author(
                rs.getInt("author_id"),
                rs.getString("author_full_name")
            )
        );
    }
}

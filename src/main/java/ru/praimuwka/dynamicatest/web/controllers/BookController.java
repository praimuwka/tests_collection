package ru.praimuwka.dynamicatest.web.controllers;

import java.util.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.praimuwka.dynamicatest.etl.entities.Book;
import ru.praimuwka.dynamicatest.etl.repositories.BookRepository;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String getBooks(Model model, RedirectAttributes ra) {
        model.addAttribute("data", bookRepository.findAll());
        return "forms/books/getAll";
    }

    @GetMapping("/create")
    public String getCreateBookForm(Book book) {
        return "forms/books/create";
    }

    @PostMapping("/create")
    public String createBook(@Valid Book book, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "forms/books/create";
        }
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("flashMessage", "Добавление выполнено успешно");
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, RedirectAttributes ra, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "forms/books/edit";
        }
        ra.addFlashAttribute("flashMessage", "Выберите существующую книгу из списка");
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable Long id, @Valid Book book, BindingResult br, RedirectAttributes ra, Model model) {
        if (book.getId()!= null && bookRepository.findById(book.getId()).isPresent()) {
            bookRepository.save(book);
        } else {
            ra.addFlashAttribute("flashMessage", "Книга не найдена");
            return "redirect:/books";
        }
        return "forms/books/edit";
    }
}

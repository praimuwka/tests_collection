package ru.praimuwka.dynamicatest.web.controllers;

import java.util.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.praimuwka.dynamicatest.etl.entities.Book;
import ru.praimuwka.dynamicatest.etl.entities.BookOperation;
import ru.praimuwka.dynamicatest.etl.entities.Client;
import ru.praimuwka.dynamicatest.etl.repositories.BookOperationRepository;
import ru.praimuwka.dynamicatest.etl.repositories.BookRepository;
import ru.praimuwka.dynamicatest.etl.repositories.ClientRepository;
import ru.praimuwka.dynamicatest.web.models.BorrowBookForm;

@Controller
@RequestMapping("/bookOperations")
public class BorrowBookController {
    private final BookOperationRepository bookOperationRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BorrowBookController(final BookRepository bookRepository, final ClientRepository clientRepository,
                                final BookOperationRepository bookOperationRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.bookOperationRepository = bookOperationRepository;
    }

    @GetMapping("/register")
    public String issueBookForm(BorrowBookForm form, Model model) {
        model.addAttribute("clientData", clientRepository.findAll());
        model.addAttribute("bookData", bookRepository.findAll());
        return "forms/bookOperations/register";
    }

    @PostMapping("/register")
    public String registerBookIssue(@Valid BorrowBookForm form, BindingResult bindingResult, Model model) {
        model.addAttribute("clientData", clientRepository.findAll());
        if (!bindingResult.hasErrors()) {
            Optional<Book> book = bookRepository.findById(form.getBookId());
            Optional<Client> client = clientRepository.findById(form.getClientId());
            if (!book.isPresent() || !client.isPresent()) {
                model.addAttribute("message", "Ошибка добавления");
            } else {
                bookOperationRepository.save(new BookOperation(new Date(), book.get(), client.get()));
                model.addAttribute("message", "Выдача успешно оформлена");
                form = new BorrowBookForm();
                model.addAttribute("form", form);
            }
        }
        return issueBookForm(form, model);
    }
}

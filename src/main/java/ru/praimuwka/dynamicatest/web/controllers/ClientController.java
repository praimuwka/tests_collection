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
import ru.praimuwka.dynamicatest.etl.entities.Client;
import ru.praimuwka.dynamicatest.etl.repositories.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String getClients(Model model, RedirectAttributes ra) {
        model.addAttribute("data", clientRepository.findAll());
        return "forms/clients/getAll";
    }

    @GetMapping("/create")
    public String getCreateClientForm(Client client) {
        return "forms/clients/create";
    }

    @PostMapping("/create")
    public String createClient(@Valid Client client, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "forms/clients/create";
        }
        clientRepository.save(client);
        redirectAttributes.addFlashAttribute("flashMessage", "Добавление выполнено успешно");
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String getClient(@PathVariable Long id, RedirectAttributes ra, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "forms/clients/edit";
        }
        ra.addFlashAttribute("flashMessage", "Выберите существующего клиента из списка");
        return "redirect:/clients";
    }

    @PostMapping("/{id}")
    public String editClient(@PathVariable Long id, @Valid Client client, BindingResult br, RedirectAttributes ra, Model model) {
        if (client.getId()!= null && clientRepository.findById(client.getId()).isPresent()) {
            clientRepository.save(client);
        } else {
            ra.addFlashAttribute("flashMessage", "Клиент не найден");
            return "redirect:/clients";
        }
        return "forms/clients/edit";
    }
}

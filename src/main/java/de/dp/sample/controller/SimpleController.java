package de.dp.sample.controller;

import de.dp.sample.model.Book;
import de.dp.sample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    private final BookRepository bookRepository;

    public SimpleController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/listBooks")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "listBooks";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book, BindingResult bindingResult, Model model) {
        System.out.println(book);
        bookRepository.saveAndFlush(book);
        model.addAttribute("books", bookRepository.findAll());
        return "listBooks";
    }
}
package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/add-book")
public class AddBookController {
    private BookService bookService;

    public AddBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAddBook(Model model) {
        Iterable<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "addbook";
    }

    @PostMapping
    public String addNewBook(@RequestParam("name") String name,
                             @RequestParam("author") List<String> authors,
                             @RequestParam("attribute") String attribute) {

        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .attribute(attribute)
                .authors(authors.stream().filter(s -> !s.equals("")).collect(Collectors.toList()))
                .build();
        this.bookService.saveNewBook(newBook);
        return "addbook";
    }

}

package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.repository.UserRepository;
import com.brs.library.service.BookService;
import com.brs.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j

@Controller
public class PageController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Book> books = new ArrayList<>(this.bookService.findAll());
        model.put("books",books);
        return "redirect:/main";
    }
    @PostMapping("/newbook")
    public String addNewBook(String name, Map<String, Object> model){
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        log.info(newBook.toString());
        this.bookService.saveNewBook(newBook);

        return "main";
    }
}
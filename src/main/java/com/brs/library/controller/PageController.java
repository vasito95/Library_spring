package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.service.BookService;
import com.brs.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Slf4j

@Controller
public class PageController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String main(Map<String, Object> model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.put("name", name);
        return "index";
    }

    @PostMapping("/newbook")
    public String addNewBook(String name, Map<String, Object> model) {
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        log.info(newBook.toString());
        this.bookService.saveNewBook(newBook);

        return "redirect:/";
    }

}
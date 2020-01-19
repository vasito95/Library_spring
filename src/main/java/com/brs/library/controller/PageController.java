package com.brs.library.controller;

import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j

@Controller
public class PageController {

    private final BookService bookService;

    public PageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String main(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("name", name);
        return "index";
    }

}
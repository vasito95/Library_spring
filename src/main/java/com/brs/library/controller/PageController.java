package com.brs.library.controller;

import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@Slf4j

@Controller
@RequestMapping("/mybooks")
public class MyBooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getMyBoooks(Map<String, Object> model){
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Book> books = this.bookService.findAllByUserId(id);
        model.put("books", books);
        return "mybooks";
    }
}

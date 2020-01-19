package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j

@Controller
@RequestMapping("/my-books")
public class MyBooksController {

    private final BookService bookService;

    public MyBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getMyBoooks(Model model){
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Book> books = this.bookService.findAllByUserId(id);
        model.addAttribute("books", books);
        return "mybooks";
    }
}

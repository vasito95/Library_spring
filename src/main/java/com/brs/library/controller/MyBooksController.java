package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping
    public String returnBook(@RequestParam(name = "bookId") Long bookId, Model model){
            this.bookService.makeBookFree(bookId);
        return "mybooks";
    }
}

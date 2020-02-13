package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import com.brs.library.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;
    private final BookService bookService;

    public AdminController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/library")
    public String getLibrary(Model model) {
        Iterable<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "library";
    }


    @GetMapping("/edit-books")
    public String getEditBooks(Model model, Pageable pageable) {
        model.addAttribute("books", this.bookService.findAllByIsInUse(false, pageable));
        return "editbooks";
    }
}

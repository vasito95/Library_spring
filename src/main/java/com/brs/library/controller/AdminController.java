package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.service.BookService;
import com.brs.library.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add-book")
    public String getAddBook(Model model) {
        Iterable<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "addbook";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        Iterable<Order> orders = this.orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/library")
    public String getLibrary(Model model) {
        Iterable<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "library";
    }

    @PostMapping("/add-book")
    public String addNewWord(String name) {
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        this.bookService.saveNewBook(newBook);
        return "redirect:add-book";
    }

    @PostMapping("/edit-book")
    public String editBook(String name) {
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        this.bookService.saveNewBook(newBook);
        return "admin";
    }

    @PostMapping("/accept-order")
    public String acceptOrder(Long bookId, Long userId, Long orderId) {
        log.warn(new String(userId + ""));
        log.warn(new String(bookId + ""));
        this.orderService.acceptOrder(bookId, userId, orderId);
        return "redirect:orders";
    }

    @PostMapping("/decline-order")
    public String declineOrder(Long orderId) {
        this.orderService.deleteOrderById(orderId);
        return "redirect:orders";
    }
}

package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.service.BookService;
import com.brs.library.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@Slf4j

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/add-book")
    public String getAddBook( Map<String, Object> model){
        Iterable<Book> books = this.bookService.findAll();
        model.put("books", books);
        return "addbook";
    }

    @GetMapping("/orders")
    public String getOrders( Map<String, Object> model){
        Iterable<Order> orders = this.orderService.findAll();
        model.put("orders", orders);
        return "orders";
    }
    @GetMapping("/library")
    public String getLibrary( Map<String, Object> model){
        Iterable<Book> books = this.bookService.findAll();
        model.put("books", books);
        return "library";
    }

    @PostMapping("/add-book")
    public String addNewWord(String name){
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        this.bookService.saveNewBook(newBook);
        return "redirect:/addbook";
    }
    @PostMapping("/edit-book")
    public String editBook(String name){
        Book newBook = Book.builder()
                .isInUse(false)
                .name(name)
                .build();
        this.bookService.saveNewBook(newBook);
        return "admin";
    }

    @PostMapping("/accept-order")
    public String acceptOrder(Long bookId, Long userId, Long orderId){
        log.warn(new String(userId + ""));
        log.warn(new String(bookId + ""));
        this.orderService.acceptOrder(bookId, userId, orderId);
        return "redirect:orders";
    }
    @PostMapping("/decline-order")
    public String declineOrder(Long orderId){
        this.orderService.deleteOrderById(orderId);
        return "redirect:orders";
    }
}

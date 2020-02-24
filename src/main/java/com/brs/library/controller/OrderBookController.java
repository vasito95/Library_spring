package com.brs.library.controller;

import com.brs.library.config.GlobalVariables;
import com.brs.library.entity.User;
import com.brs.library.exceptions.BookAlreadyTakenException;
import com.brs.library.exceptions.BookNotFoundException;
import com.brs.library.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Slf4j

@Controller
public class OrderBookController {

    private final OrderService orderService;

    public OrderBookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-book")
    public String getOrderBook(Model model) {
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusMonths(1L));
        return "orderbook";
    }

    @PostMapping("/order-book")
    public String placeOrder(String name, String dateTo, Model model, @AuthenticationPrincipal User user) {

        if(dateTo.equals("")){
            model.addAttribute("message", "Date is not correct");
        } else {
            LocalDate date = LocalDate.parse(dateTo);

            try{
                this.orderService.placeOrder(name, user.getUsername(), date, user.getId());
            } catch (BookNotFoundException notfound) {
                model.addAttribute("message", "Book not found");
            } catch (BookAlreadyTakenException bookTaken){
                model.addAttribute("message", "Books is already taken");
            }
        }
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusDays(GlobalVariables.DAYS_TO_ADD));
        return "orderbook";
    }
}

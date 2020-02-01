package com.brs.library.controller;

import com.brs.library.entity.User;
import com.brs.library.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Map;

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
    public String placeOrder(String name, String dateTo, Model model) {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String userName = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if(!dateTo.equals("")){
            LocalDate date = LocalDate.parse(dateTo);
            try{
                this.orderService.placeOrder(name, userName, date, id);
            } catch (RuntimeException e) {
                model.addAttribute("message", "Book not found");
            }
        } else {
            model.addAttribute("message", "Date is not correct");
        }
        log.warn(dateTo);
        log.warn(name);
        log.warn(id.toString());
        return "orderbook";
    }
}

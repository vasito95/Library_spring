package com.brs.library.controller;

import com.brs.library.entity.User;
import com.brs.library.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
@Slf4j

@Controller
public class OrderBookController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderbook")
    public String getOrderBook(){
        return "orderbook";
    }

    @PostMapping("/orderbook")
    public String placeOrder(String name, Date dateTo, Map<String, Object> model){
        log.warn(dateTo.toString());
        log.warn("1111111111");
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        this.orderService.placeOrder(name, LocalDate.of(dateTo.getYear(),dateTo.getMonth(), dateTo.getDay()), id);
        return "redirect:/orderbook";
    }
}

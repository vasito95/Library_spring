package com.brs.library.controller;

import com.brs.library.entity.Order;
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
@RequestMapping("/admin/orders")
public class OrdersController {

    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrders(Model model) {
        Iterable<Order> orders = this.orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/accept-order")
    public String acceptOrder(Long orderId) {
        this.orderService.acceptOrder(orderId);
        return "redirect:";
    }

    @PostMapping("/decline-order")
    public String declineOrder(Long orderId) {
        this.orderService.deleteOrderById(orderId);
        return "redirect:";
    }
}

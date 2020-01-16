package com.brs.library.service;

import com.brs.library.entity.Order;
import com.brs.library.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookService bookService;

    public void placeOrder(String name, LocalDate dateTo, Long userId) {
        Order order = Order.builder()
                .usrId(userId)
                .dateTo(dateTo)
                .bookId(this.bookService.findByName(name).getId())
                .build();
        this.orderRepository.save(order);
    }
}

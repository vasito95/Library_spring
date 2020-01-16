package com.brs.library.service;

import com.brs.library.dto.OrderDTO;
import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookService bookService;

    public void placeOrder(String bookName, String userName, LocalDate dateTo, Long userId) {
        Book book = this.bookService.findByName(bookName);
        if (book == null){
            return;
        }
        Order order = Order.builder()
                .usrId(userId)
                .dateTo(dateTo)
                .bookId(book.getId())
                .bookName(bookName)
                .userName(userName)
                .build();
        this.orderRepository.save(order);
    }

    public List<Order> findAll(){
        return this.orderRepository.findAll();
    }

    public void deleteOrderById(Long id){
        this.orderRepository.deleteById(id);
    }

    public void acceptOrder(Long bookId, Long userId, Long orderId){
        this.bookService.updateBook(bookId, userId);
        this.deleteOrderById(orderId);
    }

}

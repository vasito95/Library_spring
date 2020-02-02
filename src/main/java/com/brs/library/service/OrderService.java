package com.brs.library.service;

import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.exceptions.BookAlreadyTakenException;
import com.brs.library.exceptions.BookNotFoundException;
import com.brs.library.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookService bookService;

    public OrderService(OrderRepository orderRepository, BookService bookService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public void placeOrder(String bookName, String userName, LocalDate dateTo, Long userId) throws BookNotFoundException, BookAlreadyTakenException {
        Book book = this.bookService.findByName(bookName);
        if(book.getIsInUse()){
            throw new BookAlreadyTakenException();
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

    public void acceptOrder(Long orderId){
        Order order = orderRepository.getOne(orderId);
        this.bookService.assignBook(order);
        this.deleteOrderById(orderId);
    }

}

package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    //TODO create exception book not found
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void saveNewBook(Book b) {
        this.bookRepository.save(b);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    //TODO create own exception
    public Book findByName(String name) throws RuntimeException {
        return this.bookRepository.findBookByName(name).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public List<Book> findAllByUserId(Long id) {
        return this.bookRepository.findAllByUserId(id);
    }

    public void updateBook(Order order) {
        Book book = Book.builder()
                .id(order.getBookId())
                .inUseBy(order.getDateTo())
                .user(userService.getUserById(order.getUsrId()))
                .isInUse(true)
                .name(order.getBookName())
                .build();
        this.updateBookAsNew(book);
    }

    @Transactional
    void updateBookAsNew(Book book) {
        if (!this.findById(book.getId()).getIsInUse())
            this.bookRepository.save(book);
    }

    public List<Book> findAllWhereNameLikeAndIsInUseEquals(String name, Boolean isInUse) {
        String pattern = "%" + name + "%";
        if (name == null) {
            return (isInUse)
                    ? this.bookRepository.findAllWhereNameLikeAndIsInUseEquals("", !isInUse)
                    : this.bookRepository.findAll();
        }
        return isInUse ? this.bookRepository.findAllWhereNameLikeAndIsInUseEquals(pattern, !isInUse)
                : this.bookRepository.findAllWhereNameLike(pattern);
    }
}

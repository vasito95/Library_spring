package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.entity.User;
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

    @Transactional
    public void editBook(Book editedBook) {
        if(this.bookRepository.existsById(editedBook.getId())) {
            Book book = this.bookRepository.getOne(editedBook.getId());
            book.setAttributes(editedBook.getAttributes());
            book.setAuthors(editedBook.getAuthors());
            book.setName(editedBook.getName());
        }
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

    @Transactional
    public void makeBookFree(Long id) {
        Book book = this.findById(id);
        book.setInUseBy(null);
        book.setIsInUse(false);
        book.setUser(null);
    }

    @Transactional
    public void updateBook(Order order) {
        if(this.bookRepository.existsByIdAndIsInUseEquals(order.getBookId(),false)) {
            Book book = this.bookRepository.getOne(order.getBookId());
            book.setIsInUse(true);
            book.setInUseBy(order.getDateTo());
            book.setUser(User.builder().id(order.getUsrId()).build());
        }
    }

    public List<Book> findAllWhereNameLikeAndIsInUseEquals(String name, Boolean isInUse) {
        String pattern = "%" + name + "%";
        if (name == null) {
            return (isInUse)
                    ? this.bookRepository.findAllByIsInUse(false)
                    : this.bookRepository.findAll();
        }
        return isInUse ? this.bookRepository.findAllWhereNameLikeAndIsInUseEquals(pattern, false)
                : this.bookRepository.findAllWhereNameLike(pattern);
    }

    public List<Book> findAllByIsInUse(Boolean isInUse){
      return  this.bookRepository.findAllByIsInUse(isInUse);
    }
}

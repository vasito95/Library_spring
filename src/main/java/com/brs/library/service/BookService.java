package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.entity.Order;
import com.brs.library.entity.User;
import com.brs.library.exceptions.BookNameNotUnique;
import com.brs.library.exceptions.BookNotFoundException;
import com.brs.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
    }

    public void editBook(Book editedBook) {
        if (this.bookRepository.existsById(editedBook.getId())
                && !bookRepository.findBookByName(editedBook.getName()).isPresent()) {
            Book book = this.bookRepository.getOne(editedBook.getId());
            book.setAttribute(editedBook.getAttribute());
            book.setAuthors(editedBook.getAuthors());
            book.setName(editedBook.getName());
            try {
                bookRepository.save(book);
            } catch (Exception e){
                throw new BookNameNotUnique(e.getMessage());
            }
        }
    }


    public void saveNewBook(Book b) {
        this.bookRepository.save(b);
    }

    @Transactional
    public void deleteById(Long id) {
        if (this.bookRepository.existsByIdAndIsInUseEquals(id, false)) {
            bookRepository.deleteById(id);
        }
    }

    @Transactional
    public void makeBookFree(Long id) {
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.getOne(id);
            book.setInUseBy(null);
            book.setIsInUse(false);
            book.setUser(null);
        }
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book findById(Long id) throws BookNotFoundException {
        return this.bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Transactional
    public void assignBook(Order order) {
        if (this.bookRepository.existsByIdAndIsInUseEquals(order.getBookId(), false)) {
            Book book = this.bookRepository.getOne(order.getBookId());
            book.setIsInUse(true);
            book.setInUseBy(order.getDateTo());
            book.setUser(User.builder().id(order.getUserId()).build());
        }
    }

    public List<Book> findAllByAuthorsContains(String author) {
        return this.bookRepository.findAllByAuthorsEquals(author);
    }

    public List<Book> findAllByNameLike(String name) {
        return this.bookRepository.findAllByNameLike("%" + name + "%");
    }

    public List<Book> findAllByAttributeLike(String attribute) {
        return this.bookRepository.findAllByAttributeLike("%" + attribute + "%");
    }

    public Book findByName(String name) throws BookNotFoundException {
        return this.bookRepository.findBookByName(name).orElseThrow(BookNotFoundException::new);
    }

    public Page<Book> findAllByUserId(Long id, Pageable pageable) {
        return this.bookRepository.findAllByUserId(id, pageable);
    }

    public Page<Book> findAllByIsInUse(Boolean isInUse, Pageable pageable) {
        return this.bookRepository.findAllByIsInUse(isInUse, pageable);
    }
}

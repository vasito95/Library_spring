package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book findById(Long id) {
        return this.bookRepository.findById(id).get();
    }

    public void saveNewBook(Book b) {
        this.bookRepository.save(b);
    }


    @Transactional
    public void updateBook(Long bookId, Long userId) {
        Book book = this.bookRepository.getOne(bookId);
        book.setUserId(userId);
        this.bookRepository.save(book);
        //this.bookRepository.updateBookUserIdAndIsInUse(false, userId, bookId);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book findByName(String name) {
        return this.bookRepository.findBookByName(name);
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public List<Book> findAllByUserId(Long id) {
        return this.bookRepository.findAllByUserId(id);
    }

    public List<Book> findAllWhereNameLikeAndIsInUseEquals(String name, Boolean isInUse) {
        String pattern = "%" + name + "%";
        if (name == null) {
           if(isInUse){
               return this.bookRepository.findAllWhereNameLikeAndIsInUseEquals("", !isInUse);
           } else {
               return this.bookRepository.findAll();
           }

        }
        return isInUse ? this.bookRepository.findAllWhereNameLikeAndIsInUseEquals(pattern, !isInUse)
                : this.bookRepository.findAllWhereNameLike(pattern);

    }

}

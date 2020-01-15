package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book findById(Long id) {
        return this.bookRepository.findById(id).get();
    }

    public void saveNewBook(Book b) {
        this.bookRepository.save(b);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
    public List<Book> findAllByUserId(Long id){
        return this.bookRepository.findAllByUserId(id);
    }

    public List<Book> findAllWhereNameLikeAndIsInUseEquals(String name, Boolean isInUse) {
        String pattern = "%" + name + "%";
        if (name == null) {
            return this.bookRepository.findAll();
        }
        return isInUse ? this.bookRepository.findAllWhereNameLikeAndIsInUseEquals(pattern, isInUse)
                : this.bookRepository.findAllWhereNameLike(pattern);
    }


}

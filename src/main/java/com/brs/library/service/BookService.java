package com.brs.library.service;


import com.brs.library.entity.Book;
import com.brs.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findById(Long id) throws Exception{
        return this.bookRepository.findById(id).orElseThrow(Exception::new);

    }
}

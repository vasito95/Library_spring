package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j

@Controller
@RequestMapping("/all-books")
public class AllBooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getFiltered(@RequestParam(value = "filter", required = false) String filter, @RequestParam(value = "isFree", required = false) Boolean isFree, Map<String, Object> model){
        log.info(filter);
        log.info(Boolean.toString((Boolean.TRUE.equals(isFree))));
        List<Book> books = this.bookService.findAllWhereNameLikeAndIsInUseEquals(filter, Boolean.TRUE.equals(isFree));
        model.put("books", books);
        return "allbooks";
    }
}

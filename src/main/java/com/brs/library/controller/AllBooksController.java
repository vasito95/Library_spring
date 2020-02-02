package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Slf4j

@Controller
@RequestMapping("/all-books")
public class AllBooksController {

    private final BookService bookService;

    public AllBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getFiltered(@RequestParam(value = "filter", required = false) String filter,
                              @RequestParam(value = "field", required = false) String field,
                              Model model){
        List<Book> books = Collections.EMPTY_LIST;
        if(field!= null){
            switch (field){
                case "author" :
                    books = bookService.findAllByAuthorsContains(filter);
                    break;
                case "name" :
                    books = bookService.findAllByNameLike(filter);
                    break;
                case "attribute":
                    books = bookService.findAllByAttributeLike(filter);
                    break;
                default:
                    books = bookService.findAll();
                    break;
            }
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        return "allbooks";
    }
}

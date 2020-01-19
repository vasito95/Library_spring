package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getFiltered(@RequestParam(value = "filter", required = false) String filter, @RequestParam(value = "isFree", required = false) Boolean isFree, Model model){
        log.info(filter+"");
        log.info((isFree==Boolean.TRUE) + "");
        List<Book> books = this.bookService.findAllWhereNameLikeAndIsInUseEquals(filter, isFree==Boolean.TRUE);
        model.addAttribute("books", books);
        return "allbooks";
    }
}

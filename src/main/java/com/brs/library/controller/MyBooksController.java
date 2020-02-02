package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.entity.User;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j

@Controller
@RequestMapping("/my-books")
public class MyBooksController {

    private final BookService bookService;

    public MyBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getMyBoooks(Model model, @PageableDefault(sort = {"inUseBy"},direction = Sort.Direction.ASC) Pageable pageable){
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Page<Book> page = this.bookService.findAllByUserId(id, pageable);
        if(page.isEmpty()){
            page = this.bookService.findAllByUserId(id, pageable.first());
        }
        model.addAttribute("page", page);
        return "mybooks";
    }

    @PostMapping
    public String returnBook(@RequestParam(name = "bookId") Long bookId, Model model){
            this.bookService.makeBookFree(bookId);
        return "redirect:my-books";
    }
}

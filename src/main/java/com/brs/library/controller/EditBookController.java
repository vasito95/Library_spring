package com.brs.library.controller;

import com.brs.library.entity.Book;
import com.brs.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@Slf4j

@Controller
@RequestMapping("/edit-book")
public class EditBookController {

    private final BookService bookService;

    @Autowired
    public EditBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("{bookId}")
    public String loadBookEdit(@PathVariable(name = "bookId") Long bookId, Model model) {
        Book book = bookService.findById(bookId);
        model.addAttribute("book",book);
        return "editbook";
    }

    @PostMapping("/accept-edit")
    public String acceptEdit(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("author") List<String> authors,
                             @RequestParam("attribute") List<String> attributes, Model model){
        Book editedBook= Book.builder()
                .id(id)
                .name(name)
                .attributes(attributes)
                .authors(authors)
                .build();
        this.bookService.editBook(editedBook);
        return "redirect:/admin/edit-books";
    }

    @PostMapping("/delete")
    public String getEditBooks(@RequestParam(name = "bookId") Long bookId, Model model){
        this.bookService.deleteById(bookId);
        return "redirect:/admin/edit-books";
    }
}

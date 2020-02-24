package com.brs.library.dto;

import com.brs.library.entity.Book;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter

public class BookDTO {
    private Long id;
    private LocalDate inUseBy;
    private Boolean isInUse;
    private String name;
    private List<String> authors;
    private String attribute;
    private Long userId;

    public static BookDTO mapper(Book book){
        return builder()
                .attribute(book.getAttribute())
                .authors(book.getAuthors())
                .inUseBy(book.getInUseBy())
                .isInUse(book.getIsInUse())
                .name(book.getName())
                .id(book.getId())
                .userId(book.getUser().getId())
                .build();
    }
}

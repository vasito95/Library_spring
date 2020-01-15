package com.brs.library.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_order")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long usrId;
    private Long bookId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}

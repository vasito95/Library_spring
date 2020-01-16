package com.brs.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class OrderDTO {

    private Long usrId;
    private Long bookId;

    private String userName;
    private String bookName;
    private LocalDate dateTo;
}

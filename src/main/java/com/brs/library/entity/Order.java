package com.brs.library.entity;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long usrId;
    private Long bookId;
    private String userName;
    private String bookName;
    private LocalDate dateTo;
}

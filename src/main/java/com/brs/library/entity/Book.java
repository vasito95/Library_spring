package com.brs.library.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate inUseBy;
    private Boolean isInUse;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="author", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}

package com.brs.library.entity;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    //TODO checkout
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="attribute", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> attributes;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}

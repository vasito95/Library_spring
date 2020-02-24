package com.brs.library.repository;

import com.brs.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    boolean existsById(Long id);

    boolean existsByIdAndIsInUseEquals(Long id, Boolean isInUse);

    Optional<Book> findById(Long aLong);

    List<Book> findAllByAuthorsEquals(String author);

    List<Book> findAllByNameLike(String name);

    List<Book> findAllByAttributeLike(String attribute);

    @Override
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findBookByName(String name);

    Page<Book> findAllByUserId(Long id, Pageable pageable);

    Page<Book> findAllByIsInUse(Boolean isInUse, Pageable pageable);

    @Override
    void deleteById(Long aLong);
}

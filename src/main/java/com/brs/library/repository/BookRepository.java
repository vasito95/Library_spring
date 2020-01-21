package com.brs.library.repository;

import com.brs.library.entity.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long aLong);

    @Override
    List<Book> findAll();

    @Override
    void deleteById(Long aLong);

    Optional<Book> findBookByName(String name);

    List<Book> findAllByUserId(Long id);

    @Modifying
    @Query(value = "UPDATE Book b set b.isInUse=?1 , b.inUseBy=?2 where b.id=?3")
    void updateBookInUseByAndIsInUse(Boolean isInUser, LocalDate dateBy, Long bookId);

    @Query(value = "SELECT b FROM Book b WHERE b.name LIKE ?1 AND b.isInUse=?2")
    List<Book> findAllWhereNameLikeAndIsInUseEquals(String n, Boolean isFree);


    @Query(value = "SELECT b FROM Book b WHERE b.name LIKE ?1")
    List<Book> findAllWhereNameLike(String n);

}

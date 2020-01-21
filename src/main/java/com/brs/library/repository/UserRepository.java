package com.brs.library.repository;

import com.brs.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByUsername(String username);
    Optional<User> getUserById(Long id);
}

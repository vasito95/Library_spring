package com.brs.library.repository;

import com.brs.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByUsername(String username);
}

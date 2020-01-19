package com.brs.library.service;

import com.brs.library.entity.User;
import com.brs.library.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails u = userRepository.findByUsername(s);
        if (u != null) {
            return u;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void saveNewUser(User user) {
        this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    ;
}

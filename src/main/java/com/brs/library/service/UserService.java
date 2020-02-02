package com.brs.library.service;

import com.brs.library.entity.User;
import com.brs.library.exceptions.EmailIsNotUniqueException;
import com.brs.library.exceptions.UserNotFoundException;
import com.brs.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UserNotFoundException {
        return this.userRepository.findByEmail(s)
                .orElseThrow(() -> new UserNotFoundException("User with this email not found"));
    }

    public User getUserById(Long id){
        return this.userRepository.getUserById(id)
                .orElseThrow(RuntimeException::new);
    }

    public void saveNewUser(User user) throws EmailIsNotUniqueException {
        try{
            this.userRepository.save(user);
        } catch (Exception e){
            throw new EmailIsNotUniqueException(e.getMessage());
        }
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

}

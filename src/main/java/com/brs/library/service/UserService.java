package com.brs.library.service;

import com.brs.library.dto.UserDTO;
import com.brs.library.entity.User;
import com.brs.library.exceptions.EmailIsNotUniqueException;
import com.brs.library.exceptions.UserNotFoundException;
import com.brs.library.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
       user.setPassword(encoder.encode(user.getPassword()));
        log.warn(user.getPassword());
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

package com.brs.library.controller;

import com.brs.library.entity.Role;
import com.brs.library.entity.User;
import com.brs.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB;

        //TODO check if user exist
        try{
           userFromDB= (User) userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            user.setRoles(Collections.singleton(Role.USER));
            user.setIsActive(true);
            userService.saveNewUser(user);
            log.info(user.getUsername() + user.getPassword());
            return "redirect:/login";
        }
        return "registration";
    }
}

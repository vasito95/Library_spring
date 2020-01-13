package com.brs.library.controller;

import com.brs.library.entity.Role;
import com.brs.library.entity.User;
import com.brs.library.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepos;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userRepos.findByUsername(user.getUsername());

        if(userFromDB != null){
            model.put("message","User Exist!");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setIsActive(true);
        userRepos.save(user);
        log.info(user.getUsername() + user.getPassword());
        return "redirect:/login";
    }
}

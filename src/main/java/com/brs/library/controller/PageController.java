package com.brs.library.controller;

import com.brs.library.entity.Role;
import com.brs.library.entity.User;
import com.brs.library.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
@Slf4j

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepos;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepos.findAll();
        model.put("users", users);
        return "main";
    }
   /* @PostMapping("/main")
    public String add(@RequestParam String username, @RequestParam Role role, Map<String, Object> model){
        User user = new User(username, role);
        userRepos.save(user);
        Iterable<User> users = userRepos.findAll();
        model.put("users", users);
        return "main";
    }*/

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<User> users;

            users = userRepos.findAll();

        model.put("users", users);
        return "main";
    }

}
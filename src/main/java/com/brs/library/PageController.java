package com.brs.library;

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

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepos.findAll();
        model.put("users", users);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String username, @RequestParam String role, Map<String, Object> model){
        User user = User.builder()
                .username(username)
                .role(role)
                .build();
        userRepos.save(user);
        Iterable<User> users = userRepos.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<User> users;

        log.info(filter);
        if(filter != null && !filter.isEmpty()){
            users = userRepos.findByRole(filter);
        } else {
            users = userRepos.findAll();
        }

        model.put("users", users);
        return "main";
    }

}
package com.brs.library.controller;

import com.brs.library.entity.User;
import com.brs.library.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
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
}
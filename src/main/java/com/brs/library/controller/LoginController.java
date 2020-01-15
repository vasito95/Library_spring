package com.brs.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("message", "User do not Exist!");
        }
        return "login";
    }


}

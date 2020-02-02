package com.brs.library.controller;

import com.brs.library.entity.Role;
import com.brs.library.entity.User;
import com.brs.library.exceptions.EmailIsNotUniqueException;
import com.brs.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration( Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute(value = "user") User user, BindingResult errors, Model model){

        if(errors.hasErrors()){
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setIsActive(true);
        try{
            userService.saveNewUser(user);
        } catch (EmailIsNotUniqueException e) {
            model.addAttribute("message", "registration.email.not.unique");
            return "registration";
        }
        return "redirect:/login";
    }
}

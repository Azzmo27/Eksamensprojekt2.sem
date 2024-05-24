package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/newRegistration")
    public String registerUser(User user) {
        userService.createUser(user);
        return "redirect:/create";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}

package com.manager.br.controller;

import com.manager.br.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String homeInformation(Model model, HttpSession session){

        model.addAttribute("title", "Home Page");
        model.addAttribute("contentFragment", "fragments/home");

        return "dashboard";
    }

}

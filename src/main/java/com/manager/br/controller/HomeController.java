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
        // Assuming the 'roles' attribute is already set as a List<String> in the session
        List<String> roles = (List<String>) session.getAttribute("roles");

        // If roles are not present, get them using the userService (assuming a username is present in session)
        if (roles == null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                roles = userService.getUserRole(username);
                session.setAttribute("roles", roles);
            }
        }

        model.addAttribute("title", "Home Page");
        model.addAttribute("contentFragment", "fragments/home");
        model.addAttribute("roles", roles); // Adding roles to the model for Thymeleaf

        return "dashboard";
    }

}

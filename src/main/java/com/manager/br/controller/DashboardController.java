package com.manager.br.controller;

import com.manager.br.util.Roles;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) session.getAttribute("roles");
        roles.forEach(System.out::println);
        if (roles == null) {
            roles = Collections.emptyList(); // Initialize roles to an empty list if null
        }
        model.addAttribute("roles", roles);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("contentFragment", "fragments/home");
        return "dashboard";
    }
}

package com.manager.br.controller;

import com.manager.br.dto.BookDto;
import com.manager.br.dto.CategoryDto;
import com.manager.br.dto.UserDto;
import com.manager.br.service.BookService;
import com.manager.br.service.CategoryService;
import com.manager.br.service.UserService;
import com.manager.br.util.Roles;
import com.manager.br.model.CategoryModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAdminPage(Model model, HttpSession session) {
        List<String> roles = (List<String>) session.getAttribute("roles");
        if (roles == null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                roles = userService.getUserRole(username);
                session.setAttribute("roles", roles);
            }
        }

        model.addAttribute("roles", roles);


        List<CategoryModel> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("contentFragment", "fragments/admin");
        model.addAttribute("title", "Admin Dashboard");

        return "dashboard";
    }



    @PostMapping("/books/create")
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/admin";
    }

    @PostMapping("/books/edit")
    public String editBook(@RequestParam long bookId, @ModelAttribute BookDto bookDto) {
        bookService.editBook(bookId, bookDto);
        return "redirect:/admin";
    }

    @PostMapping("/categories/create")
    public String createCategory(@RequestParam String category) {
        categoryService.createCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute UserDto userDto, @RequestParam Roles role) {
        userService.createUser(userDto, role);
        return "redirect:/admin";
    }
}

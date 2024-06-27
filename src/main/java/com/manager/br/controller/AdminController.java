package com.manager.br.controller;

import com.manager.br.dto.BookDto;
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

    @GetMapping("/books")
    public String getBooksPage(Model model) {
        List<CategoryModel> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Manage Books");
        model.addAttribute("contentFragment", "fragments/admin/books-admin");
        return "dashboard";
    }

    @PostMapping("/books/create")
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/admin/books";
    }

    @PostMapping("/books/edit")
    public String editBook(@RequestParam long bookId, @ModelAttribute BookDto bookDto) {
        bookService.editBook(bookId, bookDto);
        return "redirect:/admin/books";
    }

    @GetMapping("/categories")
    public String getCategoriesPage(Model model) {
        model.addAttribute("title", "Manage Categories");
        model.addAttribute("contentFragment", "fragments/admin/category :: category");
        return "dashboard";
    }

    @PostMapping("/categories/create")
    public String createCategory(@RequestParam String category) {
        categoryService.createCategory(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/edit")
    public String editCategory(@RequestParam long categoryId, @RequestParam String categoryName) {
        categoryService.editCategory(categoryId, categoryName);
        return "redirect:/admin/categories";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("title", "Manage Users");
        model.addAttribute("contentFragment", "fragments/admin/user :: user");
        return "dashboard";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute UserDto userDto, @RequestParam Roles role) {
        userService.createUser(userDto, role);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/edit")
    public String editUser(@RequestParam long userId, @ModelAttribute UserDto userDto) {
        userService.editUser(userId, userDto);
        return "redirect:/admin/users";
    }
}

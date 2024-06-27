package com.manager.br.controller;

import com.manager.br.dto.UserDto;
import com.manager.br.service.LogInService;
import com.manager.br.service.UserService;
import com.manager.br.util.Messages;
import com.manager.br.util.Roles;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LogInController {
    @Autowired
    private LogInService logInService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("pass") String pass,
                        Model model,
                        HttpSession session){

        if (logInService.isPassValid(userName, pass)){
            session.setAttribute("username", userName);
            model.addAttribute("title", "Home Page");
            model.addAttribute("contentFragment", "fragments/home");
            return "redirect:/dashboard";
        }
        model.addAttribute("message", Messages.INCORRECT.getText());
        model.addAttribute("messageStyle", Messages.RED.getText());
        return "login";
    }

    @GetMapping("/create")
    public String createUser(){
        return "create-user";
    }


        @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto userDto, Model model){

        if (userDto.getPass().equals(userDto.getConfirmPass())){
            if(logInService.isPassStrong(userDto.getPass())){
                userService.createUser(userDto);
                model.addAttribute("message", Messages.SUCCESS.getText());
                model.addAttribute("messageStyle", Messages.GREEN.getText());
                return "login";

            }
            model.addAttribute("message", Messages.WEAK_PASSWORD.getText());
            model.addAttribute("messageStyle", Messages.RED.getText());
            return "create-user";
        }
        model.addAttribute("message", Messages.PASSWORD_MISMATCH.getText());
        model.addAttribute("messageStyle", Messages.RED.getText());
        return "create-user";
    }
}

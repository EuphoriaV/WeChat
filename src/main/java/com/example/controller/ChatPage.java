package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ChatPage extends Page {
    private final UserService userService;

    public ChatPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/chat/{login}")
    public String getChat(@PathVariable String login, Model model, HttpSession session) {
        if (getUser(session) == null) {
            return "redirect:/enter";
        }
        User user = userService.findByLogin(login);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("currentUser", user);
        return "ChatPage";
    }
}

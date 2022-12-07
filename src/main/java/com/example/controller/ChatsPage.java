package com.example.controller;

import com.example.domain.User;
import com.example.service.MessageService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatsPage extends Page {
    private final MessageService messageService;
    private final UserService userService;

    public ChatsPage(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping({"/chats", "", "/"})
    public String getChats(Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) {
            return "redirect:/enter";
        }
        model.addAttribute("chats", messageService.findChats(user));
        return "ChatsPage";
    }

    @PostMapping({"/chats", "", "/"})
    public String search(@RequestParam("search") String str, HttpSession session, Model model) {
        User user = getUser(session);
        if (user == null) {
            return "redirect:/enter";
        }
        List<User> users = userService.findAllUsers(str);
        model.addAttribute("users", users);
        return "UsersPage";
    }
}

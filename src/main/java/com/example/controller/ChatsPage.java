package com.example.controller;

import com.example.domain.User;
import com.example.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChatsPage extends Page {
    private final MessageService messageService;

    public ChatsPage(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping({"/chats", "", "/"})
    public String getChats(Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) {
            return "redirect:/enter";
        }
        model.addAttribute("friends", messageService.findFriends(user));
        return "ChatsPage";
    }
}

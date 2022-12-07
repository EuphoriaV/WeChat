package com.example.controller;

import com.example.domain.User;
import com.example.form.MessageForm;
import com.example.service.MessageService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ChatPage extends Page {
    private final UserService userService;
    private final MessageService messageService;

    public ChatPage(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/chat/{login}")
    public String getChat(@PathVariable String login, Model model, HttpSession session) {
        User from = getUser(session);
        if (from == null) {
            return "redirect:/enter";
        }
        User to = userService.findByLogin(login);
        if (to == null) {
            return "redirect:/";
        }
        model.addAttribute("currentUser", to);
        model.addAttribute("messages", messageService.findMessages(from, to));
        model.addAttribute("messageForm", new MessageForm());
        return "ChatPage";
    }

    @PostMapping("/chat/{login}")
    public String postChat(@PathVariable String login, @Valid @ModelAttribute("messageForm") MessageForm messageForm,
                           BindingResult bindingResult, HttpSession session, Model model) {
        User from = getUser(session);
        if (from == null) {
            return "redirect:/enter";
        }
        User to = userService.findByLogin(login);
        if (to == null) {
            return "redirect:/chats";
        }
        model.addAttribute("currentUser", to);
        model.addAttribute("messages", messageService.findMessages(from, to));
        if (bindingResult.hasErrors()) {
            return "ChatPage";
        }
        messageService.sendMessage(messageForm, from, to);
        return "redirect:/chat/{login}";
    }
}

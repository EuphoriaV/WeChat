package com.euphoriav.wechat.controller;

import com.euphoriav.wechat.dto.SendMessageRequest;
import com.euphoriav.wechat.helper.BindingResultHelper;
import com.euphoriav.wechat.service.MessageService;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatsPage extends AbstractPage {

    private final MessageService messageService;
    private final UserService userService;
    private final BindingResultHelper bindingResultHelper;

    @GetMapping
    public String getChats(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        var user = user(userDetails);
        model.addAttribute("chats", messageService.getChats(user));
        return "ChatsPage";
    }

    @GetMapping("/{login}")
    public String getChat(@PathVariable("login") String login, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        var from = user(userDetails);
        var to = userService.loadUserByUsername(login);
        setModel(from, to, model);
        return "ChatPage";
    }

    @PostMapping("/{login}")
    public String sendMessage(@PathVariable("login") String login, @Valid @ModelAttribute("sendMessageRequest") SendMessageRequest sendMessageRequest,
                              BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        var from = user(userDetails);
        var to = userService.loadUserByUsername(login);
        if (bindingResult.hasErrors()) {
            bindingResultHelper.saveErrorsToModel(bindingResult, model);
            setModel(from, to, model);
            return "ChatPage";
        }
        messageService.sendMessage(sendMessageRequest, from, to);
        setModel(from, to, model);
        return "ChatPage";
    }

    private void setModel(User from, User to, Model model) {
        model.addAttribute("to", to);
        model.addAttribute("messages", messageService.getMessagesBetween(from, to));
    }
}

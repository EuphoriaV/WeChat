package com.euphoriav.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatsPage extends AbstractPage {

    @GetMapping
    public String getChats() {
        return "ChatsPage";
    }

    @GetMapping("/{login}")
    public String getChat(@PathVariable("login") String login) {
        return "ChatPage";
    }
}

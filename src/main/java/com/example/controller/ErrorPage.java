package com.example.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPage implements ErrorController {
    @RequestMapping("/error")
    public String error() {
        return "redirect:/chats";
    }
}

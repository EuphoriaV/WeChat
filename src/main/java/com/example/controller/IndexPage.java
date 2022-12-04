package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page {
    @GetMapping({"", "/"})
    public String getIndex() {
        return "NotFoundPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        unsetUser(session);
        return "redirect:/";
    }
}

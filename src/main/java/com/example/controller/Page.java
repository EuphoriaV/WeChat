package com.example.controller;

import com.example.domain.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

public class Page {
    @ModelAttribute("user")
    public User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public void unsetUser(HttpSession session) {
        session.removeAttribute("user");
    }
}

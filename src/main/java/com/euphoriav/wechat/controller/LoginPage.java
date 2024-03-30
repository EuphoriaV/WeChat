package com.euphoriav.wechat.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginPage extends AbstractPage {

    @GetMapping("/login")
    public String getLogin() {
        return "LoginPage";
    }

    @ModelAttribute("error")
    public String error(HttpServletRequest request) {
        return request.getParameter("error");
    }
}

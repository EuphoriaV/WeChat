package com.euphoriav.wechat.controller;

import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersPage extends AbstractPage {

    private final UserService userService;

    @GetMapping
    public String search(@RequestParam("search") String search, Model model) {
        search = search.trim();
        List<User> users = userService.getUsersBySubstr(search);
        model.addAttribute("users", users);
        model.addAttribute("search", search);
        return "UsersPage";
    }
}

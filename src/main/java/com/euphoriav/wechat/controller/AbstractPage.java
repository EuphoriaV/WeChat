package com.euphoriav.wechat.controller;

import com.euphoriav.wechat.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractPage {

    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal UserDetails user) {
        return (User) user;
    }
}

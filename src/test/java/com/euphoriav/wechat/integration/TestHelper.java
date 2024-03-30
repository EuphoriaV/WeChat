package com.euphoriav.wechat.integration;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestHelper {

    private final UserService userService;

    public User register(String login, String name, String password) {
        var request = new RegisterRequest(login, name, password, password);
        var httpRequest = new MockHttpServletRequest();
        var httpResponse = new MockHttpServletResponse();
        userService.register(request, httpRequest, httpResponse);
        return userService.loadUserByUsername(login);
    }
}

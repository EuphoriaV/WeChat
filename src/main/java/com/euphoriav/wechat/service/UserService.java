package com.euphoriav.wechat.service;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.helper.AuthHelper;
import com.euphoriav.wechat.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthHelper authHelper;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s was not found", login))
        );
    }

    public void register(RegisterRequest registerRequest, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setLogin(registerRequest.getLogin());
        user.setName(registerRequest.getName());
        user.setEncodedPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        log.info("user was registered {}", user);
        authHelper.saveAuthentication(user, request, response);
    }
}

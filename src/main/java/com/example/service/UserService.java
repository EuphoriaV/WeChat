package com.example.service;

import com.example.domain.User;
import com.example.form.RegisterForm;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginVacant(String login) {
        return !userRepository.existsByLogin(login);
    }

    public User register(RegisterForm registerForm) {
        User user = new User();
        user.setName(registerForm.getName());
        user.setLogin(registerForm.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), user.getLogin(), registerForm.getPassword());
        return user;
    }

    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findAllUsers(String str) {
        return userRepository.findAllUsers(str.trim());
    }
}

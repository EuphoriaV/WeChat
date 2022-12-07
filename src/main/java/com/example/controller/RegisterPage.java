package com.example.controller;

import com.example.form.RegisterForm;
import com.example.form.validator.RegisterFormValidator;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class RegisterPage extends Page {
    private final UserService userService;
    private final RegisterFormValidator registerFormValidator;

    public RegisterPage(UserService userService, RegisterFormValidator registerFormValidator) {
        this.userService = userService;
        this.registerFormValidator = registerFormValidator;
    }

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerFormValidator);
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "RegisterPage";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "RegisterPage";
        }
        httpSession.setAttribute("user", userService.register(registerForm));
        return "redirect:/chats";
    }
}

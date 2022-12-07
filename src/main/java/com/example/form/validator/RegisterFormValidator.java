package com.example.form.validator;

import com.example.form.RegisterForm;
import com.example.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterFormValidator implements Validator {
    private final UserService userService;

    public RegisterFormValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            RegisterForm registerForm = (RegisterForm) target;
            if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
                errors.rejectValue("password", "passwords.are-not-equals", "пароли должны совпадать");
                errors.rejectValue("passwordConfirm", "passwords.are-not-equals", "пароли должны совпадать");
            }
            if (!userService.isLoginVacant(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is-in-use", "логин уже занят");
            }
        }
    }
}

package com.example.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.form.EnterForm;
import com.example.service.UserService;

@Component
public class EnterFormValidator implements Validator {
    private final UserService userService;

    public EnterFormValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return EnterForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            EnterForm enterForm = (EnterForm) target;
            if (userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.rejectValue("password", "password.is-wrong", "пароль или логин не верен");
                errors.rejectValue("login", "login.is-wrong", "пароль или логин не верен");
            }
        }
    }
}

package com.euphoriav.wechat.helper.validator;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegisterRequestValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest req = (RegisterRequest) target;
        if (!req.getPassword().equals(req.getPasswordConfirmation())) {
            errors.rejectValue("passwordConfirmation", "error.password", "пароли не совпадают");
        }
        if (userRepository.existsByLogin(req.getLogin())) {
            errors.rejectValue("login", "error.login", "логин уже занят");
        }
    }
}

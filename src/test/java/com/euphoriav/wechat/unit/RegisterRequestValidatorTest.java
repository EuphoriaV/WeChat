package com.euphoriav.wechat.unit;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.helper.validator.RegisterRequestValidator;
import com.euphoriav.wechat.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.SimpleErrors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterRequestValidatorTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegisterRequestValidator registerRequestValidator;

    @Test
    public void validationFailedPasswordConfirmationTest() {
        RegisterRequest registerRequest = new RegisterRequest("euphoriav", "Илья", "1234", "1235");
        var errors = new SimpleErrors(registerRequest);
        when(userRepository.existsByLogin(registerRequest.getLogin())).thenReturn(false);

        registerRequestValidator.validate(registerRequest, errors);

        assertEquals(1, errors.getFieldErrorCount());
        assertTrue(errors.hasFieldErrors("passwordConfirmation"));
        assertEquals("пароли не совпадают", errors.getFieldError("passwordConfirmation").getDefaultMessage());
    }

    @Test
    public void validationFailedLoginTest() {
        RegisterRequest registerRequest = new RegisterRequest("euphoriav", "Илья", "1234", "1234");
        var errors = new SimpleErrors(registerRequest);
        when(userRepository.existsByLogin(registerRequest.getLogin())).thenReturn(true);

        registerRequestValidator.validate(registerRequest, errors);

        assertEquals(1, errors.getFieldErrorCount());
        assertTrue(errors.hasFieldErrors("login"));
        assertEquals("логин уже занят", errors.getFieldError("login").getDefaultMessage());
    }

    @Test
    public void validationSucceedTest() {
        RegisterRequest registerRequest = new RegisterRequest("euphoriav", "Илья", "1234", "1234");
        var errors = new SimpleErrors(registerRequest);
        when(userRepository.existsByLogin(registerRequest.getLogin())).thenReturn(false);

        registerRequestValidator.validate(registerRequest, errors);

        assertEquals(0, errors.getFieldErrorCount());
    }
}

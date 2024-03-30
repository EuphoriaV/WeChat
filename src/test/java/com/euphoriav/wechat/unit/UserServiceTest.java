package com.euphoriav.wechat.unit;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.helper.AuthHelper;
import com.euphoriav.wechat.repository.UserRepository;
import com.euphoriav.wechat.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthHelper authHelper;
    @InjectMocks
    private UserService userService;

    @Test
    public void loadByUsernameTest() {
        User user = new User();
        user.setLogin("euphoriav");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        var actual = userService.loadUserByUsername(user.getLogin());

        assertEquals(user, actual);
    }

    @Test
    public void loadByUsernameFailedTest() {
        when(userRepository.findByLogin("euphoriav")).thenReturn(Optional.empty());

        var e = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("euphoriav"));
        assertEquals(e.getMessage(), "User euphoriav was not found");
    }

    @Test
    public void registerTest() {
        var request = new RegisterRequest("euphoriav", "Илья", "1234", "1234");
        var httpRequest = new MockHttpServletRequest();
        var httpResponse = new MockHttpServletResponse();

        userService.register(request, httpRequest, httpResponse);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(userArgumentCaptor.capture());
        var savedUser = userArgumentCaptor.getValue();
        verify(authHelper).saveAuthentication(savedUser, httpRequest, httpResponse);
        assertEquals(request.getLogin(), savedUser.getLogin());
        assertEquals(request.getName(), savedUser.getName());
    }

    @Test
    public void getUsersBySubstrTest() {
        var substr = "euphoriav";

        userService.getUsersBySubstr(substr);

        verify(userRepository).getUsersBySubstr(String.format("%%%s%%", substr));
    }
}

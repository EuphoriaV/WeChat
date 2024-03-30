package com.euphoriav.wechat.integration.service;

import com.euphoriav.wechat.integration.AbstractIntegrationTest;
import com.euphoriav.wechat.integration.TestHelper;
import com.euphoriav.wechat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private TestHelper testHelper;

    @Test
    public void registerAndLoadByUsernameTest() {
        String login = "euphoriav", name = "Илья", password = "1234";
        testHelper.register(login, name, password);

        var user = userService.loadUserByUsername(login);

        assertEquals(login, user.getLogin());
        assertEquals(name, user.getName());
    }

    @Test
    public void getUsersBySubstrTest() {
        String login1 = "euphoriav", name1 = "Илья", password1 = "1234";
        testHelper.register(login1, name1, password1);
        String login2 = "skarina", name2 = "Илья Скарина", password2 = "1234";
        testHelper.register(login2, name2, password2);

        var expectedBothUsers = userService.getUsersBySubstr("илья");
        var expectedOneUser = userService.getUsersBySubstr("euphoria");

        assertEquals(2, expectedBothUsers.size());
        assertEquals(1, expectedOneUser.size());
        assertEquals(login1, expectedBothUsers.get(0).getLogin());
        assertEquals(login2, expectedBothUsers.get(1).getLogin());
        assertEquals(login1, expectedOneUser.get(0).getLogin());
    }
}

package com.euphoriav.wechat.integration;

import com.euphoriav.wechat.dto.Chat;
import com.euphoriav.wechat.dto.SendMessageRequest;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest extends AbstractIntegrationTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private TestHelper testHelper;

    @Test
    public void sendMessageAndGetMessagesBetweenTest() {
        String login1 = "euphoriav", name1 = "Илья", password1 = "1234";
        String login2 = "skarina", name2 = "Илья Скарина", password2 = "1234";
        User user1 = testHelper.register(login1, name1, password1), user2 = testHelper.register(login2, name2, password2);
        var req1 = new SendMessageRequest("hello");
        var req2 = new SendMessageRequest("bye");
        messageService.sendMessage(req1, user1, user2);
        messageService.sendMessage(req2, user2, user1);
        messageService.sendMessage(req1, user1, user1);
        messageService.sendMessage(req2, user2, user2);

        var actualMessages = messageService.getMessagesBetween(user1, user2);

        assertEquals(2, actualMessages.size());
        assertEquals(req1.getText(), actualMessages.get(0).getText());
        assertEquals(req2.getText(), actualMessages.get(1).getText());
        assertEquals(user1, actualMessages.get(0).getFrom());
        assertEquals(user2, actualMessages.get(0).getTo());
        assertEquals(user2, actualMessages.get(1).getFrom());
        assertEquals(user1, actualMessages.get(1).getTo());
    }

    @Test
    public void getChatsTest() {
        String login1 = "euphoriav", name1 = "Илья", password1 = "1234";
        String login2 = "skarina", name2 = "Илья Скарина", password2 = "1234";
        User user1 = testHelper.register(login1, name1, password1), user2 = testHelper.register(login2, name2, password2);
        var req1 = new SendMessageRequest("hello");
        var req2 = new SendMessageRequest("bye");
        var req3 = new SendMessageRequest("qq");
        messageService.sendMessage(req1, user1, user2);
        messageService.sendMessage(req2, user2, user1);
        messageService.sendMessage(req3, user1, user1);
        messageService.sendMessage(req2, user2, user2);

        var actualChats = messageService.getChats(user1);

        assertEquals(2, actualChats.size());
        Chat chat1 = actualChats.get(0), chat2 = actualChats.get(1);
        assertEquals(user1, chat1.getUser());
        assertEquals(user2, chat2.getUser());
        assertEquals(req3.getText(), chat1.getMessage().getText());
        assertEquals(req2.getText(), chat2.getMessage().getText());
        assertEquals(user1, chat1.getMessage().getFrom());
        assertEquals(user1, chat1.getMessage().getTo());
        assertEquals(user2, chat2.getMessage().getFrom());
        assertEquals(user1, chat2.getMessage().getTo());
    }
}

package com.euphoriav.wechat.unit;

import com.euphoriav.wechat.dto.SendMessageRequest;
import com.euphoriav.wechat.entity.Message;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.repository.MessageRepository;
import com.euphoriav.wechat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;

    @Test
    public void getMessagesBetweenTest() {
        User user1 = new User(), user2 = new User();
        user1.setId(295387L);
        user2.setId(295388L);

        messageService.getMessagesBetween(user1, user2);

        verify(messageRepository).getMessagesBetween(user1.getId(), user2.getId());
    }

    @Test
    public void sendMessageTest() {
        User from = new User(), to = new User();
        from.setId(295387L);
        to.setId(295388L);
        SendMessageRequest request = new SendMessageRequest("hello");

        messageService.sendMessage(request, from, to);

        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(messageArgumentCaptor.capture());
        var savedMessage = messageArgumentCaptor.getValue();
        assertEquals(savedMessage.getText(), request.getText());
        assertEquals(savedMessage.getFrom().getId(), from.getId());
        assertEquals(savedMessage.getTo().getId(), to.getId());
    }

    @Test
    public void getChatsTest() {
        User user1 = new User(), user2 = new User();
        user1.setId(295387L);
        user2.setId(295388L);
        Message message1 = new Message(), message2 = new Message();
        message1.setFrom(user1);
        message1.setTo(user2);
        message1.setCreatedAt(LocalDateTime.now());
        message1.setText("bye");
        message2.setFrom(user1);
        message2.setTo(user1);
        message2.setCreatedAt(LocalDateTime.now().plusDays(1));
        message2.setText("hello");
        List<Message> messages = List.of(message1, message2);
        when(messageRepository.getLastMessages(user1.getId())).thenReturn(messages);

        var actual = messageService.getChats(user1);

        assertEquals(2, actual.size());
        assertEquals(message2, actual.get(0).getMessage());
        assertEquals(user1, actual.get(0).getUser());
        assertEquals(message1, actual.get(1).getMessage());
        assertEquals(user2, actual.get(1).getUser());
    }
}

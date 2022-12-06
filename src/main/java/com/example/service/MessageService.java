package com.example.service;

import com.example.domain.ChatPOJO;
import com.example.domain.Message;
import com.example.domain.User;
import com.example.form.MessageForm;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(MessageForm messageForm, User from, User to) {
        Message message = new Message();
        message.setText(messageForm.getText());
        message.setFrom(from);
        message.setTo(to);
        messageRepository.save(message);
    }

    public List<Message> findMessages(User a, User b) {
        return messageRepository.findMessages(a.getId(), b.getId());
    }

    public List<ChatPOJO> findChats(User user) {
        List<Object[]> users = messageRepository.findFriends(user.getId());
        List<ChatPOJO> chats = new ArrayList<>();
        for (Object[] objects : users) {
            User user1 = new User();
            BigInteger id = (BigInteger) objects[0];
            user1.setId(id.longValue());
            user1.setLogin((String) objects[1]);
            user1.setName((String) objects[2]);
            Message message = messageRepository.findLastMessage(user.getId(), user1.getId());
            ChatPOJO chat = new ChatPOJO();
            chat.setUser(user1);
            chat.setMessage(message);
            chats.add(chat);
        }
        return chats;
    }
}

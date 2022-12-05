package com.example.service;

import com.example.domain.Message;
import com.example.domain.User;
import com.example.form.MessageForm;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;

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

    public List<String> findFriends(User user) {
        return messageRepository.findFriends(user.getId());
    }
}

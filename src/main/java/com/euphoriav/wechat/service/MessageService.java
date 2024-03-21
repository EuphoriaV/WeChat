package com.euphoriav.wechat.service;

import com.euphoriav.wechat.dto.SendMessageRequest;
import com.euphoriav.wechat.entity.Message;
import com.euphoriav.wechat.entity.User;
import com.euphoriav.wechat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getMessagesBetween(User user1, User user2) {
        return messageRepository.getMessagesBetween(user1.getId(), user2.getId());
    }

    public void sendMessage(SendMessageRequest sendMessageRequest, User from, User to) {
        Message message = new Message();
        message.setText(sendMessageRequest.getText());
        message.setFrom(from);
        message.setTo(to);

        messageRepository.save(message);
        log.info("message was sent {}", message);
    }
}

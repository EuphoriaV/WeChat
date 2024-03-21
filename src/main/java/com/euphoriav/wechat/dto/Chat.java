package com.euphoriav.wechat.dto;

import com.euphoriav.wechat.entity.Message;
import com.euphoriav.wechat.entity.User;
import lombok.Data;

@Data
public class Chat {

    private final User user;
    private final Message message;
}

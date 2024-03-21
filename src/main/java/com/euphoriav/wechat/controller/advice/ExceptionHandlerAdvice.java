package com.euphoriav.wechat.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.error("{}", e.getMessage());
        return "redirect:/chats";
    }
}

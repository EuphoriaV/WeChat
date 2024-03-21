package com.euphoriav.wechat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SendMessageRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1024)
    private final String text;
}

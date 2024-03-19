package com.euphoriav.wechat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[a-z0-9_-]+", message = "логин может содержать только маленькие латинские буквы, цифры и символы подчеркивания")
    private final String login;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private final String name;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 30)
    private final String password;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 30)
    private final String passwordConfirmation;
}

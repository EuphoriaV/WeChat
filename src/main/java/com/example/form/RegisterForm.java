package com.example.form;

import javax.validation.constraints.*;

public class RegisterForm {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[a-z0-9_-]+", message = "логин может содержать только маленькие латинские буквы, цифры и символы подчеркивания")
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 30)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 30)
    private String passwordConfirm;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}

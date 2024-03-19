package com.euphoriav.wechat.controller;

import com.euphoriav.wechat.dto.RegisterRequest;
import com.euphoriav.wechat.helper.BindingResultHelper;
import com.euphoriav.wechat.helper.validator.RegisterRequestValidator;
import com.euphoriav.wechat.logic.RegisterOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterPage extends AbstractPage {

    private final BindingResultHelper bindingResultHelper;
    private final RegisterOperation registerOperation;
    private final RegisterRequestValidator registerCustomEditor;

    @InitBinder("registerRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerCustomEditor);
    }

    @GetMapping
    public String getRegister() {
        return "RegisterPage";
    }

    @PostMapping
    public String postRegister(@Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest, BindingResult bindingResult,
                               Model model, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            bindingResultHelper.saveErrorsToModel(bindingResult, model);
            return "RegisterPage";
        }
        registerOperation.activate(registerRequest, request, response);
        return "redirect:/chats";
    }
}

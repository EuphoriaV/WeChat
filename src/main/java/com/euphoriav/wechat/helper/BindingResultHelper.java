package com.euphoriav.wechat.helper;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class BindingResultHelper {

    public void saveErrorsToModel(BindingResult bindingResult, Model model) {
        var errors = bindingResult.getFieldErrors();
        Map<String, String> fieldToMessage = new HashMap<>();
        errors.forEach(error -> fieldToMessage.putIfAbsent(error.getField(), error.getDefaultMessage()));
        model.addAttribute("errors", fieldToMessage);
    }
}

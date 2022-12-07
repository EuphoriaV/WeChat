package com.example.controller;

import com.example.form.EnterForm;
import com.example.form.validator.EnterFormValidator;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EnterPage extends Page {
    private final UserService userService;
    private final EnterFormValidator enterFormValidator;

    public EnterPage(UserService userService, EnterFormValidator enterFormValidator) {
        this.userService = userService;
        this.enterFormValidator = enterFormValidator;
    }

    @InitBinder("enterForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(enterFormValidator);
    }

    @GetMapping("/enter")
    public String getEnter(Model model) {
        model.addAttribute("enterForm", new EnterForm());
        return "EnterPage";
    }

    @PostMapping("/enter")
    public String postEnter(@Valid @ModelAttribute("enterForm") EnterForm enterForm, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "EnterPage";
        }
        httpSession.setAttribute("user", userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()));
        return "redirect:/chats";
    }
}

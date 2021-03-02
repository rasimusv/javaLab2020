package ru.itis.rasimusv.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.SignInForm;
import ru.itis.rasimusv.services.UsersService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class SignInController {

    private final UsersService usersService;

    public SignInController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/signin")
    public String getPage(HttpSession session) {

        session.setAttribute("Authenticated", "false");
        return "signin";
    }

    @PostMapping(value = "/signin")
    public String logIn(SignInForm form, HttpSession session) {

        if (usersService.correctPassword(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        }
        return "signin";
    }
}


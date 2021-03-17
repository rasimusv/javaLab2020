package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.SignInForm;
import ru.itis.rasimusv.services.UsersService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    /*@PostMapping(value = "/signin")
    public String logIn(@Valid SignInForm form, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });

            model.addAttribute("signInForm", form);

        } else if (usersService.correctPassword(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        }
        return "signin";
    }*/

    @PostMapping(value = "/signin")
    public String logIn(@Valid SignInForm form, BindingResult bindingResult, Model model, HttpSession session) {

        if (usersService.correctPassword(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        }
        return "signin";
    }
}


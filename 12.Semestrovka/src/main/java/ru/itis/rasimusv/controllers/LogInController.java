package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.LogInForm;
import ru.itis.rasimusv.services.UsersService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LogInController {

    private final UsersService usersService;

    public LogInController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/login")
    public String getPage(HttpSession session) {

        session.setAttribute("Authenticated", "false");
        return "login";
    }

    /*@PostMapping(value = "/login")
    public String logIn(@Valid LogInForm form, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });

            model.addAttribute("logInForm", form);

        } else if (usersService.correctPassword(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        }
        return "login";
    }*/

    @PostMapping(value = "/login")
    public String logIn(@Valid LogInForm form, BindingResult bindingResult, Model model, HttpSession session) {

        if (usersService.correctPassword(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        }
        return "login";
    }
}


package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.LogInForm;
import ru.itis.rasimusv.services.LogInService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


@Controller
public class LogInController {

    /*private final LogInService logInService;

    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }
     */

    @GetMapping(value = "/login")
    public String getPage(Model model, HttpSession session) {
        model.addAttribute("logInForm", new LogInForm());
        session.setAttribute("Authenticated", "false");
        return "login";
    }

    /*@PostMapping(value = "/login")
    public String logIn(@Valid LogInForm form, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("logInForm.ValidCredentials")) {
                    model.addAttribute("credentialsErrorMessage", error.getDefaultMessage());
                }
                return true;
            });

            model.addAttribute("logInForm", form);
            return "login";
        }
        if (logInService.correctPassword(form)) {
            return "redirect:/success";
        } else {
            return "login";
        }
    }
     */
}


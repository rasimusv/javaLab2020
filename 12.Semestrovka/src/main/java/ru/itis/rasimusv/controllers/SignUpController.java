package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.SignUpForm;
import ru.itis.rasimusv.services.SignUpService;

import javax.servlet.http.HttpSession;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @GetMapping("/signup")
    public String getSignUpPage(Model model, HttpSession session) {
        model.addAttribute("signUpForm", new SignUpForm());
        session.setAttribute("Authenticated", "false");
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(SignUpForm form, HttpSession session) {

        if (signUpService.signUp(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/";
        } else {
            return "signup";
        }
    }
}

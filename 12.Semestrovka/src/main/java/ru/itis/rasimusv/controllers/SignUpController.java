package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.rasimusv.forms.SignUpForm;
import ru.itis.rasimusv.services.SignUpService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model, HttpSession session) {
        model.addAttribute("signUpForm", new SignUpForm());
        session.setAttribute("Authenticated", "false");
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid SignUpForm form, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("signUpForm", form);
            return "signup";
        }

        if (signUpService.signUp(form)) {
            session.setAttribute("Authenticated", "true");
            return "redirect:/success";
        } else {
            return "signup";
        }
    }
}

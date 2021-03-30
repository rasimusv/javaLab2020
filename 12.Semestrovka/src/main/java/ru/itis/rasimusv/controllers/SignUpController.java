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
import java.util.Objects;

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
    public String signUp(@Valid SignUpForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.FieldMatch")) {
                    model.addAttribute("passwordMismatchErrorMessage", error.getDefaultMessage());
                }
                return true;
            });

            model.addAttribute("signUpForm", form);
            return "signup";
        }
        signUpService.signUp(form);
        return "redirect:/login";
    }
}

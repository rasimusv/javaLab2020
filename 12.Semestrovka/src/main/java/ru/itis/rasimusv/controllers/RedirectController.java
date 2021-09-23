package ru.itis.rasimusv.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RedirectController {

    @Value("${anatolik.2021}")
    private String redirectAddress;

    @GetMapping(value = "/anatolik2021")
    public String doRedirect() {
        return "redirect:" + redirectAddress;
    }

}

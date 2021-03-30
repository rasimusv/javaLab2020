package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.rasimusv.services.UsersService;

@Controller
public class ConfirmController {

    private final UsersService usersService;

    public ConfirmController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/confirm/{confirm-code}", method = RequestMethod.GET)
    public String confirm(@PathVariable("confirm-code") String confirmCode, Model model) {
        usersService.confirm(confirmCode);
        return "redirect:/success";
    }
}

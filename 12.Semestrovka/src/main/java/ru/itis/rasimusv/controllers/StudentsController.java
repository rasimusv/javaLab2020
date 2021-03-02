package ru.itis.rasimusv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.rasimusv.services.StudentsService;

import javax.servlet.http.Cookie;


@Controller
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping(value = "/students")
    public String getPage(@CookieValue(required = false, name = "color") Cookie cookie, Model model) {
        model.addAttribute("studentsList", studentsService.getAllStudents());
        model.addAttribute("colorCookie", cookie);
        return "students";
    }

    @PostMapping(value = "/students")
    public String setColor(@RequestParam("color") String color,
                           @CookieValue(required = false, name = "color") Cookie cookie) {

        cookie.setValue(color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        return "redirect:/students";
    }

}

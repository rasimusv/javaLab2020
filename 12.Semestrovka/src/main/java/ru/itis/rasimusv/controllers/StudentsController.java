package ru.itis.rasimusv.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.rasimusv.services.StudentsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping(value = "/students")
    public String getPage(@CookieValue(required = false, name = "color") String cookie, Model model) {
        model.addAttribute("studentsList", studentsService.getAllStudents());
        model.addAttribute("colorCookie", cookie);
        return "students";
    }

    @PostMapping(value = "/students")
    public String setColor(@RequestParam("color") String color,
                           HttpServletResponse response) {

        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);

        return "redirect:/students";
    }

}

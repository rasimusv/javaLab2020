package ru.itis.rasimusv.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.services.UsersService;

import java.util.List;

@Slf4j
@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/users")
    public String getPage(Model model) {
        model.addAttribute("usersList", usersService.getAllViewUsers());
        return "users";
    }

    @GetMapping(value = "/confirm/{confirm-code}")
    public String confirm(@PathVariable("confirm-code") String confirmCode) {
        usersService.confirm(confirmCode);
        return "redirect:/";
    }

    @RequestMapping(value = "/users/{user-id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return usersService.getUser(userId);
    }

    @RequestMapping(value = "/users/json",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> addUserFromJson(@RequestBody UserDto user) {
        usersService.addUser(user);
        return usersService.getAllUsers();
    }

}

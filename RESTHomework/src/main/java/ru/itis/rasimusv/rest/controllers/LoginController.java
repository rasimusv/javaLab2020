package ru.itis.rasimusv.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rasimusv.rest.dto.RefreshTokenDto;
import ru.itis.rasimusv.rest.dto.AccessTokenDto;
import ru.itis.rasimusv.rest.services.LoginService;
import ru.itis.rasimusv.rest.dto.CredentialsDto;
import ru.itis.rasimusv.rest.dto.EmailPasswordDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody EmailPasswordDto emailPassword, HttpServletResponse response) {
        RefreshTokenDto tokenDto = loginService.login(emailPassword);

        Cookie cookie = new Cookie("refreshToken", tokenDto.getRefreshToken());

        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        try {
            return ResponseEntity.ok(new AccessTokenDto(tokenDto.getAccessToken()));
        } catch (Throwable throwable) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody CredentialsDto credentialsDto) {
        loginService.signUp(credentialsDto);
    }

}

package ru.itis.rasimusv.rest.token;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.rasimusv.rest.services.TokensService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessTokenFilter extends OncePerRequestFilter {

    private final TokensService tokensService;

    public AccessTokenFilter(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        boolean success = false;
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                String refreshToken = cookie.getValue();
                String accessToken = tokensService.newToken(refreshToken).getToken();
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.print("{\n  \"token\": \"" + accessToken + "\"\n}");
                writer.flush();
                success = true;
                break;
            }
        }
        if (!success) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

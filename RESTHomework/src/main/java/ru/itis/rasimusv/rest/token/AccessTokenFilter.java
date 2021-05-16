package ru.itis.rasimusv.rest.token;

import org.springframework.stereotype.Component;
import ru.itis.rasimusv.rest.services.TokensService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessTokenFilter implements Filter {

    private final TokensService tokensService;

    public AccessTokenFilter(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;
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

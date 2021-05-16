package ru.itis.rasimusv.rest.security.token;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.rasimusv.rest.services.TokenBlacklistService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenLogoutFilter extends OncePerRequestFilter {

    private final TokenBlacklistService tokenBlacklistService;
    private final RequestMatcher logoutRequest = new AntPathRequestMatcher("/logout", "GET");

    public TokenLogoutFilter(TokenBlacklistService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            tokenBlacklistService.add(request.getHeader("X-TOKEN"));
            SecurityContextHolder.clearContext();
            return;
        }

        filterChain.doFilter(request, httpServletResponse);
    }
}

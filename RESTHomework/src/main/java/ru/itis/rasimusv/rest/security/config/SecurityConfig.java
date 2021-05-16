package ru.itis.rasimusv.rest.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.rasimusv.rest.security.token.TokenAuthenticationFilter;
import ru.itis.rasimusv.rest.security.token.TokenAuthenticationProvider;
import ru.itis.rasimusv.rest.security.token.TokenLogoutFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final TokenLogoutFilter tokenLogoutFilter;

    public SecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter, TokenAuthenticationProvider tokenAuthenticationProvider, TokenLogoutFilter tokenLogoutFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.tokenLogoutFilter = tokenLogoutFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http
                .addFilterAt(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(tokenLogoutFilter, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers("/teachers").hasAuthority("ADMIN")
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").hasAnyAuthority()
                .antMatchers("/signup").permitAll()
                .antMatchers("/token").permitAll()
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }
}

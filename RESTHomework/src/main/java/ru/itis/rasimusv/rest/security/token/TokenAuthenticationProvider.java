package ru.itis.rasimusv.rest.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.rasimusv.rest.models.User;
import ru.itis.rasimusv.rest.security.details.UserDetailsImpl;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Value( "${jwt.secret-key}" )
    private String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(tokenAuthentication.getToken());

            UserDetails userDetails = new UserDetailsImpl(User.builder()
                    .email(decodedJWT.getClaim("email").asString())
                    .hashPassword("")
                    .role(User.Role.valueOf(decodedJWT.getClaim("role").asString()))
                    .state(User.State.valueOf(decodedJWT.getClaim("state").asString()))
                    .build());

            tokenAuthentication.setAuthenticated(true);
            tokenAuthentication.setUserDetails(userDetails);
        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
            tokenAuthentication.setAuthenticated(false);
        }

        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}

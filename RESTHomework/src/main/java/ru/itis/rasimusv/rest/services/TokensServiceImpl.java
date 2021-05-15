package ru.itis.rasimusv.rest.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.dto.RefreshTokenDto;
import ru.itis.rasimusv.rest.dto.AccessTokenDto;
import ru.itis.rasimusv.rest.models.RefreshToken;
import ru.itis.rasimusv.rest.models.User;
import ru.itis.rasimusv.rest.repositories.RefreshTokensRepository;

import java.util.Date;
import java.util.function.Supplier;

@Service
public class TokensServiceImpl implements TokensService {

    @Value( "${jwt.secret-key}" )
    private String secretKey;

    private final RefreshTokensRepository refreshTokensRepository;

    public TokensServiceImpl(RefreshTokensRepository refreshTokensRepository) {
        this.refreshTokensRepository = refreshTokensRepository;
    }

    @SneakyThrows
    @Override
    public AccessTokenDto newToken(RefreshTokenDto refreshTokenDto) {
        return newToken(refreshTokenDto.getRefreshToken());
    }

    @SneakyThrows
    @Override
    public AccessTokenDto newToken(String refreshToken) {
        RefreshToken result = refreshTokensRepository.findByToken(refreshToken).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Token not found"));
        User user = result.getUser();

        String accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .sign(Algorithm.HMAC256(secretKey));

        return new AccessTokenDto(accessToken);
    }

}

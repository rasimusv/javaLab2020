package ru.itis.rasimusv.rest.services;

import ru.itis.rasimusv.rest.dto.RefreshTokenDto;
import ru.itis.rasimusv.rest.dto.AccessTokenDto;

public interface TokensService {
    AccessTokenDto newToken(RefreshTokenDto refreshTokenDto);
    AccessTokenDto newToken(String refreshToken);
}

package ru.itis.rasimusv.rest.services;

import ru.itis.rasimusv.rest.dto.RefreshTokenDto;
import ru.itis.rasimusv.rest.dto.CredentialsDto;
import ru.itis.rasimusv.rest.dto.EmailPasswordDto;

public interface LoginService {
    RefreshTokenDto login(EmailPasswordDto emailPassword);

    void signUp(CredentialsDto credentialsDto);
}

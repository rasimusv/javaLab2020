package ru.itis.rasimusv.rest.services;

import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.dto.CredentialsDto;
import ru.itis.rasimusv.rest.dto.EmailPasswordDto;
import ru.itis.rasimusv.rest.dto.RefreshTokenDto;
import ru.itis.rasimusv.rest.models.RefreshToken;
import ru.itis.rasimusv.rest.models.User;
import ru.itis.rasimusv.rest.repositories.RefreshTokensRepository;
import ru.itis.rasimusv.rest.repositories.UsersRepository;

import java.util.UUID;
import java.util.function.Supplier;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokensRepository refreshTokensRepository;
    private final TokensService tokensService;

    public LoginServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RefreshTokensRepository refreshTokensRepository, TokensService tokensService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokensRepository = refreshTokensRepository;
        this.tokensService = tokensService;
    }

    @SneakyThrows
    @Override
    public RefreshTokenDto login(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {
            String refreshTokenValue = UUID.randomUUID().toString();
            RefreshToken token = RefreshToken.builder()
                    .token(refreshTokenValue)
                    .user(user)
                    .build();

            refreshTokensRepository.save(token);

            return RefreshTokenDto.builder()
                    .refreshToken(refreshTokenValue)
                    .accessToken(tokensService.newToken(refreshTokenValue).getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public void signUp(CredentialsDto dto) {
        usersRepository.save(User.builder()
                .email(dto.getEmail())
                .hashPassword(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .state(User.State.valueOf(dto.getState()))
                .role(User.Role.valueOf(dto.getRole()))
                .build());
    }
}

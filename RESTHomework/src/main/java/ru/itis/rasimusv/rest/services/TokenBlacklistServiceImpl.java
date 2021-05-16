package ru.itis.rasimusv.rest.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.repositories.BlacklistRepository;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final BlacklistRepository blacklistRepository;

    public TokenBlacklistServiceImpl(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    public void add(String token) {
        blacklistRepository.save(token);
    }

    @Override
    public boolean exists(String token) {
        return blacklistRepository.exists(token);
    }
}
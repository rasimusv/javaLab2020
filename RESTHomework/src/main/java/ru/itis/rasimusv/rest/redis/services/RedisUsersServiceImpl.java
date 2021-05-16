package ru.itis.rasimusv.rest.redis.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.models.User;
import ru.itis.rasimusv.rest.redis.models.RedisUser;
import ru.itis.rasimusv.rest.redis.repository.RedisUsersRepository;
import ru.itis.rasimusv.rest.repositories.UsersRepository;
import ru.itis.rasimusv.rest.services.TokenBlacklistService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisUsersServiceImpl implements RedisUsersService {

    private final UsersRepository usersRepository;
    private final TokenBlacklistService blacklistService;
    private final RedisUsersRepository redisUsersRepository;

    public RedisUsersServiceImpl(UsersRepository usersRepository, TokenBlacklistService blacklistService, RedisUsersRepository redisUsersRepository) {
        this.usersRepository = usersRepository;
        this.blacklistService = blacklistService;
        this.redisUsersRepository = redisUsersRepository;
    }

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUsersRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }
        redisUsersRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        usersRepository.save(user);
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blacklistService.add(token);
            }
            redisUser.getTokens().clear();
            redisUsersRepository.save(redisUser);
        }
    }
}
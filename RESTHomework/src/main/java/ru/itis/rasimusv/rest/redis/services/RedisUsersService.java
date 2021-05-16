package ru.itis.rasimusv.rest.redis.services;

import ru.itis.rasimusv.rest.models.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
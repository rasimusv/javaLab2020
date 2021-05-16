package ru.itis.rasimusv.rest.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.models.User;
import ru.itis.rasimusv.rest.redis.services.RedisUsersService;
import ru.itis.rasimusv.rest.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RedisUsersService redisUsersService;

    public UsersServiceImpl(UsersRepository usersRepository, RedisUsersService redisUsersService) {
        this.usersRepository = usersRepository;
        this.redisUsersService = redisUsersService;
    }

    @Override
    public void blockUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        redisUsersService.addAllTokensToBlackList(user);
    }
}
package ru.itis.rasimusv.rest.redis.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.rasimusv.rest.repositories.BlacklistRepository;

@Repository
public class BlacklistRepositoryRedisTemplateImpl implements BlacklistRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public BlacklistRepositoryRedisTemplateImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        return hasToken != null && hasToken;
    }
}
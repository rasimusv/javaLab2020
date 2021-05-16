package ru.itis.rasimusv.rest.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.rasimusv.rest.redis.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
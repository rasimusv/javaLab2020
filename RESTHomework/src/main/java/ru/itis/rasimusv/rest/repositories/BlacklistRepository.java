package ru.itis.rasimusv.rest.repositories;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}
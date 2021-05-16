package ru.itis.rasimusv.rest.services;

public interface TokenBlacklistService {
    void add(String token);

    boolean exists(String token);
}
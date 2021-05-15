package ru.itis.rasimusv.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.rest.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

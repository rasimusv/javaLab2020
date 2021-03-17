package ru.itis.rasimusv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByConfirmCode(String confirmCode);

}

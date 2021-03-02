package ru.itis.rasimusv.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.rasimusv.models.User;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Repository
public class UsersRepositoryFakeImpl implements UsersRepository<Long> {

    @Override
    public void save(User user) {}

    @Override
    public void delete(Long id) {}

    @Override
    public void update(User user) {
        delete(user.getId());
        save(user);
    }

    @Override
    public List<User> findById(Long id) {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L)
                .username("fake")
                .firstName("FAKE")
                .lastName("FAKEOFF")
                .email("fake@gmail.com")
                .hashPassword("q")
                .build());
        return users;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<User> findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findByConfirmCode(String confirmCode) {
        return null;
    }
}


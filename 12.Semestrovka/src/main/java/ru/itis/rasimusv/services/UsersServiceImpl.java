package ru.itis.rasimusv.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.dto.ViewUserDto;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

import static ru.itis.rasimusv.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .hashPassword(userDto.getHashPassword())
                .confirmCode(userDto.getConfirmCode())
                .state(userDto.getState())
                .build());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public List<ViewUserDto> getAllViewUsers() {
        return ViewUserDto.from(getAllUsers());
    }

    @Override
    public List<ViewUserDto> getAllViewUsers(int page, int size) {
        return ViewUserDto.from(getAllUsers());
    }

    @Override
    public Optional<UserDto> findUserByUsername(String username) {
        return from(usersRepository.findByUsername(username));
    }

    @Override
    public boolean containsUserWithUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<UserDto> userDto = UserDto.from(usersRepository.findById(userId));
        return userDto.orElse(null);
    }

    @Override
    public void confirm(String confirmCode) {
        Optional<User> mayBeUser = usersRepository.findByConfirmCode(confirmCode);
        if (mayBeUser.isPresent()) {
            User user = mayBeUser.get();
            user.setState(User.State.CONFIRMED);
            usersRepository.save(user);
        }
    }

    @Override
    public boolean containsUserWithEmail(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean correctCredentials(String username, String password) {
        Optional<UserDto> mayBeUser = findUserByUsername(username);

        if(mayBeUser.isPresent()) {
            UserDto user = mayBeUser.get();
            String hashPassword = user.getHashPassword();
            return (hashPassword != null) && (passwordEncoder.matches(password, hashPassword));
        }
        return false;
    }

}

package ru.itis.rasimusv.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.dto.ViewUserDto;
import ru.itis.rasimusv.forms.SignInForm;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

import static ru.itis.rasimusv.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository<Long> usersRepository;

    public UsersServiceImpl(UsersRepository<Long> usersRepository, PasswordEncoder passwordEncoder) {
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
    public List<UserDto> getAllUsers(int page, int size) {
        return from(usersRepository.findAll(page, size));
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
        return getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public boolean containsUserWithUsername(String username) {
        return !usersRepository.findByUsername(username).isEmpty();
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<UserDto> userDto = from(usersRepository.findById(userId)).stream().findFirst();
        return userDto.orElse(null);
    }

    @Override
    public boolean correctPassword(SignInForm userDto) {
        UserDto mayBeUser = findUserByUsername(userDto.getUsername()).orElse(null);

        if(mayBeUser != null) {
            String hashPassword = mayBeUser.getHashPassword();
            return (hashPassword != null) && (passwordEncoder.matches(userDto.getPassword(), hashPassword));
        }
        return false;
    }

    @Override
    public void confirm(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode).get(0);
        user.setState(User.State.CONFIRMED);
        usersRepository.update(user);
    }

}

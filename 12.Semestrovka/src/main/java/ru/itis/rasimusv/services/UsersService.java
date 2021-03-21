package ru.itis.rasimusv.services;

import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.dto.ViewUserDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    void addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    List<ViewUserDto> getAllViewUsers();

    List<ViewUserDto> getAllViewUsers(int page, int size);

    Optional<UserDto> findUserByUsername(String username);

    boolean containsUserWithUsername(String username);

    UserDto getUser(Long userId);

    void confirm(String confirmCode);

    boolean containsUserWithEmail(String email);

    boolean correctCredentials(String username, String password);
}


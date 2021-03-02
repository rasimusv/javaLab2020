package ru.itis.rasimusv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rasimusv.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public static ViewUserDto from(User user) {
        if (user == null) {
            return null;
        }
        return ViewUserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static ViewUserDto from(UserDto user) {
        if (user == null) {
            return null;
        }
        return ViewUserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static List<ViewUserDto> from(List<UserDto> users) {
        return users.stream()
                .map(ViewUserDto::from)
                .collect(Collectors.toList());
    }
}


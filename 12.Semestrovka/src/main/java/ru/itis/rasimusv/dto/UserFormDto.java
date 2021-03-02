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
public class UserFormDto {
    private Long id;
    private String email;

    public static UserFormDto from(User user) {
        return UserFormDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static List<UserFormDto> from(List<User> users) {
        return users.stream()
                .map(UserFormDto::from)
                .collect(Collectors.toList());
    }
}

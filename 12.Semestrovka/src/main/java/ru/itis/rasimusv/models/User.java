package ru.itis.rasimusv.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private String confirmCode;
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }



}

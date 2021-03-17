package ru.itis.rasimusv.models;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private String confirmCode;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }


}

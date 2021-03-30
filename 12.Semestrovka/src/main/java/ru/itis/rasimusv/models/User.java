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

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum State {
        CONFIRMED, NOT_CONFIRMED, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return isConfirmed() && !isBanned();
    }

    public boolean isConfirmed() {
        return this.state == State.CONFIRMED;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

}

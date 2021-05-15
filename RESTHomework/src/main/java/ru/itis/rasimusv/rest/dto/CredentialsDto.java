package ru.itis.rasimusv.rest.dto;

import lombok.Data;

@Data
public class CredentialsDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String state;
    private String role;

}

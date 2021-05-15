package ru.itis.rasimusv.rest.dto;

import lombok.Data;

@Data
public class EmailPasswordDto {
    private String email;
    private String password;
}

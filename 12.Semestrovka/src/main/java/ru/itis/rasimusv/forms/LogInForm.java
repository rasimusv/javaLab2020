package ru.itis.rasimusv.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.itis.rasimusv.validation.ValidCredentials;
import ru.itis.rasimusv.validation.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidCredentials(message = "{errors.invalid.credentials}", username = "username", password = "password")
public class LogInForm {

    @NotBlank(message = "{errors.invalid.null}")
    @Size(min = 4, max = 50, message = "{errors.invalid.username_size}")
    private String username;

    @NotBlank(message = "{errors.invalid.null}")
    @ValidPassword(message = "{errors.invalid.password_chars}")
    @Size(min = 8, max = 50, message = "{errors.invalid.password_length}")
    private String password;
}

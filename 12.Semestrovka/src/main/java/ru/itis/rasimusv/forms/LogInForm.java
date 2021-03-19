package ru.itis.rasimusv.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.itis.rasimusv.validation.ValidPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInForm {

    private String username;

    //@ValidPassword(message = "{errors.invalid.password}")
    private String password;
}

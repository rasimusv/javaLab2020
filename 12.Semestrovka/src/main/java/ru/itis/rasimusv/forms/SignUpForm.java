package ru.itis.rasimusv.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rasimusv.validation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname = "lastName"
)
@FieldMatch(first = "password",
        second = "repeatPassword",
        message = "{errors.invalid.password_mismatch}"
)
public class SignUpForm {

    @NotBlank(message = "{errors.invalid.null}")
    @Size(min = 4, max = 50, message = "{errors.invalid.username_size}")
    @AvailableUsername(message = "{errors.invalid.username_already_exists}")
    private String username;

    @NotBlank(message = "{errors.invalid.null}")
    private String firstName;

    @NotBlank(message = "{errors.invalid.null}")
    private String lastName;

    @Email(message = "{errors.invalid.email}")
    @AvailableEmail(message = "{errors.invalid.email_already_exists}")
    private String email;

    @ValidPassword(message = "{errors.invalid.password_chars}")
    @Size(min = 8, max = 50, message = "{errors.invalid.password_length}")
    private String password;

    @NotBlank(message = "{errors.invalid.null}")
    private String repeatPassword;

}

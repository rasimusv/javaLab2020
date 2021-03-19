package ru.itis.rasimusv.forms;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
/*@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname = "lastName"
)*/
public class SignUpForm {

    private String username;
    private String firstName;
    private String lastName;

    @Email(message = "{errors.incorrect.email}")
    private String email;

    //@ValidPassword(message = "{errors.invalid.password}")
    private String password;

    private String repeatPassword;

}

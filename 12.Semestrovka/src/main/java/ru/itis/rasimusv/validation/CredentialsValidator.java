package ru.itis.rasimusv.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.rasimusv.services.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CredentialsValidator implements ConstraintValidator<ValidCredentials, Object> {

    @Autowired
    UsersService usersService;

    private String usernamePropertyName;
    private String passwordPropertyName;

    @Override
    public void initialize(ValidCredentials constraintAnnotation) {
        this.usernamePropertyName = constraintAnnotation.username();
        this.passwordPropertyName = constraintAnnotation.password();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String username = (String) new BeanWrapperImpl(value).getPropertyValue(usernamePropertyName);
        String password = (String) new BeanWrapperImpl(value).getPropertyValue(passwordPropertyName);

        return usersService.correctCredentials(username, password);
    }
}

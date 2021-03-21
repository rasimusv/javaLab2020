package ru.itis.rasimusv.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.rasimusv.services.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAvailabilityValidator implements ConstraintValidator<AvailableEmail, String> {

    @Autowired
    UsersService usersService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !usersService.containsUserWithEmail(value);
    }

}

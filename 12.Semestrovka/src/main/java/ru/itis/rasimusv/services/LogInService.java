package ru.itis.rasimusv.services;

import ru.itis.rasimusv.forms.LogInForm;

public interface LogInService {
    boolean correctPassword(LogInForm userDto);

}

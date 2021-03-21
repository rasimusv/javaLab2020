package ru.itis.rasimusv.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.forms.LogInForm;

@Service
public class LogInServiceImpl implements LogInService {

    private final UsersService usersService;

    public LogInServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean correctPassword(LogInForm form) {
        return usersService.correctCredentials(form.getUsername(), form.getPassword());
    }
}

package ru.itis.rasimusv.services;

import ru.itis.rasimusv.forms.SignUpForm;

public interface SignUpService {

    boolean usernameIsAvailable(SignUpForm form);

    boolean emailIsAvailable(SignUpForm form);

    boolean signUp(SignUpForm form);
}

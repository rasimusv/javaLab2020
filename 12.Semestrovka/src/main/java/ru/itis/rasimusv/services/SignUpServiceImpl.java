package ru.itis.rasimusv.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.forms.SignUpForm;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.util.EmailUtil;
import ru.itis.rasimusv.util.MailsGenerator;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;

    private final UsersService usersService;

    private final MailsGenerator mailsGenerator;

    private final EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.username}")
    private String from;

    @Value("${mail.message.subject}")
    private String subject;

    public SignUpServiceImpl(MailsGenerator mailsGenerator, EmailUtil emailUtil, UsersService usersService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.mailsGenerator = mailsGenerator;
        this.emailUtil = emailUtil;
        this.usersService = usersService;
    }

    @Override
    public boolean usernameIsAvailable(SignUpForm form) {
        return !usersService.containsUserWithUsername(form.getUsername());
    }

    @Override
    public boolean emailIsAvailable(SignUpForm form) {
        return !usersService.containsUserWithEmail(form.getEmail());
    }

    @Override
    public boolean signUp(SignUpForm form) {

        if (form.getPassword().equals(form.getRepeatPassword()) && usernameIsAvailable(form) && emailIsAvailable(form)) {
            String hashPassword = passwordEncoder.encode(form.getPassword());

            UserDto newUser = UserDto.builder()
                    .username(form.getUsername())
                    .firstName(form.getFirstName())
                    .lastName(form.getLastName())
                    .email(form.getEmail())
                    .hashPassword(hashPassword)
                    .confirmCode(UUID.randomUUID().toString())
                    .state(User.State.NOT_CONFIRMED)
                    .build();

            usersService.addUser(newUser);

            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
            emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
            return true;
        }
        return false;
    }
}

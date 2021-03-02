package ru.itis.rasimusv.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.UserDto;
import ru.itis.rasimusv.forms.SignUpForm;
import ru.itis.rasimusv.models.User;
import ru.itis.rasimusv.util.EmailUtil;
import ru.itis.rasimusv.util.MailsGenerator;

import java.util.UUID;

@Slf4j
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
    public boolean signUp(SignUpForm form) {

        if (form.getPassword().equals(form.getRepeatPassword()) && !usersService.containsUserWithUsername(form.getUsername())) {
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

            log.error(newUser.toString());

            usersService.addUser(newUser);

            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
            emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
            return true;
        }
        return false;
    }
}

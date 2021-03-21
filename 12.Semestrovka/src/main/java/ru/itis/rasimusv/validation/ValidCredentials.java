package ru.itis.rasimusv.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CredentialsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCredentials {
    String message() default "Invalid credentials";

    String username();
    String password();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

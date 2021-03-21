package ru.itis.rasimusv.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameAvailabilityValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AvailableUsername {
    String message() default "Username is not available";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

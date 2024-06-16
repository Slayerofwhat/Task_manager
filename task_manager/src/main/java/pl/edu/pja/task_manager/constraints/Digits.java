package pl.edu.pja.task_manager.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DigitsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Digits {
    String message() default "{pl.edu.pja.tpo_11.Password.digits}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

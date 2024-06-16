package pl.edu.pja.task_manager.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UpperCaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCase {
    String message() default "{pl.edu.pja.tpo_11.Password.upperCase}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

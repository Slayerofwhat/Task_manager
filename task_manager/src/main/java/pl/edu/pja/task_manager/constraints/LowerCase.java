package pl.edu.pja.task_manager.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LowerCaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LowerCase {
    String message() default "{pl.edu.pja.tpo_11.Password.lowerCase}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package pl.edu.pja.task_manager.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Date;

public class FutureDateValidator implements ConstraintValidator<FutureDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }
        return date.after(new Date());
    }
}

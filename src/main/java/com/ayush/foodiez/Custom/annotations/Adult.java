package com.ayush.foodiez.Custom.annotations;
import java.lang.annotation.*;

import com.ayush.foodiez.Custom.validator.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Adult {
    String message() default "Below 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

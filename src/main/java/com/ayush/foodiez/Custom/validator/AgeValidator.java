package com.ayush.foodiez.Custom.validator;

import com.ayush.foodiez.Custom.annotations.Adult;
import com.ayush.foodiez.dto.UserSignUpDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Adult, Integer> {
    @Override
    public void initialize(Adult constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value>=18;
    }
}

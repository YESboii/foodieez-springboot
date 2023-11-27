package com.ayush.foodiez.Custom.validator;

import com.ayush.foodiez.Custom.annotations.PasswordMatchers;
import com.ayush.foodiez.dto.UserSignUpDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers,UserSignUpDto>{
    @Override
    public void initialize(PasswordMatchers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserSignUpDto value, ConstraintValidatorContext context) {
            var flag =  value.getPassword().compareTo(value.getRePass())==0;
            if(!flag){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Password do not match")
                        .addPropertyNode("rePass")
                        .addConstraintViolation();
            }
            return flag;
    }
}

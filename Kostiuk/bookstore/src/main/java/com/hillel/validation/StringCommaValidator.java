package com.hillel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringCommaValidator implements ConstraintValidator<OnCommaSeparated, String> {


    public void initialize(OnCommaSeparated constraint) {
        //NOP
    }

    public boolean isValid(String string, ConstraintValidatorContext context) {
        return string.trim().replace(" ","").matches("[a-zA-Z\\s,]+");
    }

}

package com.wooread.wooreadbase.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value))
            return true;
        if (value.length() > 11)
            return false;
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}

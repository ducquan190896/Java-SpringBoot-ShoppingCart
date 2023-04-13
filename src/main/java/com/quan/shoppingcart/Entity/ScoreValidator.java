package com.quan.shoppingcart.Entity;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String> {
    List<String> scores = Arrays.asList("A", "A+", "A-", "B", "B+", "B-");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for(String scor : scores) {
            if(scor.equals(value)) {
                return true;
            }
        }
        return false;
    }
}

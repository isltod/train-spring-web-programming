package com.mycompany.ordersystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    /**
     * 정규식
     *1. 전체 길이는 6에서 20 문자임
     *2. 하나의 @, #, $, % 와 같은 특수문자를 포함해야 함
     *3. 하나의 대문자와 소문자를 포함해야 함
     *4. 하나의 숫자를 포함해야 함
     */

    private String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Matcher matcher = Pattern.compile(passwordPattern).matcher(password);
        return matcher.matches();
    }
}

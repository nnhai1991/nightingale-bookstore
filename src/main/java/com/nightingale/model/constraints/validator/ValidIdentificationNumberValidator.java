package com.nightingale.model.constraints.validator;


import com.nightingale.model.constraints.ValidIdentificationNumber;
import com.nightingale.util.UtilConstants;
import com.nightingale.util.web.UtilValidation;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidIdentificationNumberValidator implements ConstraintValidator<ValidIdentificationNumber, Object> {

    private String identificationTypeId;
    private String identificationNumber;


    @Override
    public void initialize(ValidIdentificationNumber constraintAnnotation) {
        this.identificationTypeId = constraintAnnotation.identificationTypeId();
        this.identificationNumber = constraintAnnotation.identificationNumber();
    }

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        return true;
    }

    private final int[] Multiples = {2, 7, 6, 5, 4, 3, 2};

    private Integer extractNumnber(String str)  {

        return Integer.parseInt(str.substring(1, str.length() - 1));
    }

    private boolean isNRICValid(String nric) {

        if (nric == null || nric.length() != 9)
            return false;

        int total = 0, count = 0, numericNric = 0;
        char first = nric.charAt(0);
        char last = nric.charAt(nric.length() - 1);

        if (first != 'S' && first != 'T') {
            return false;
        }

        try {
            numericNric=extractNumnber(nric);
        } catch (Exception e) {
            return false;
        }

        while (numericNric != 0) {
            total += numericNric % 10 * Multiples[Multiples.length - (1 + count++)];

            numericNric /= 10;
        }

        char[] outputs;
        if (first == 'S') {
            outputs = new char[]{'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
        } else {
            outputs = new char[]{'G', 'F', 'E', 'D', 'C', 'B', 'A', 'J', 'Z', 'I', 'H'};
        }

        return last == outputs[total % 11];

    }

    public boolean isFinValid(String fin) {
        if (fin == null || fin.length() != 9)
            return false;

        int total = 0, count = 0, numericNric = 0;
        char first = fin.charAt(0);
        char last = fin.charAt(fin.length() - 1);

        if (first != 'F' && first != 'G') {
            return false;
        }

        try {
            numericNric=extractNumnber(fin);
        } catch (Exception e) {
            return false;
        }

        while (numericNric != 0) {
            total += numericNric % 10 * Multiples[Multiples.length - (1 + count++)];

            numericNric /= 10;
        }

        char[] outputs;
        if (first == 'F') {
            outputs = new char[]{'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'};
        } else {
            outputs = new char[]{'R', 'Q', 'P', 'N', 'M', 'L', 'K', 'X', 'W', 'U', 'T'};
        }

        return last == outputs[total % 11];
    }
}

package com.nightingale.model.constraints.validator;

import com.nightingale.model.constraints.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String first;
    private String second;

    public void initialize(final FieldMatch constraintAnnotation) {
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
    }

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {


        try {
            final Object firstObj = BeanUtils.getProperty(value, first);
            final Object secondObj = BeanUtils.getProperty(value, second);

            return firstObj.equals(secondObj);

        } catch (final Exception ignore) {
        }

        return true;
    }
}

package com.nightingale.app.model.constraints.validator;

import com.nightingale.app.model.constraints.DayDurationDoesNotExceedDates;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * Created by hai on 4/7/2017.
 */
public class DayDurationDoesNotExceedDatesValidator implements ConstraintValidator<DayDurationDoesNotExceedDates, Object> {


    private String startDateField;
    private String endDateField;

    private String numberOfDaysField;

    @Override
    public void initialize(DayDurationDoesNotExceedDates constraintAnnotation) {

        this.startDateField = constraintAnnotation.startDateField();
        this.endDateField = constraintAnnotation.endDateField();
        this.numberOfDaysField = constraintAnnotation.numberOfDaysField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        LocalDateTime startDate = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(startDateField);
        LocalDateTime endDate = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(endDateField);
        Integer numberOfDays = (Integer) new BeanWrapperImpl(value).getPropertyValue(numberOfDaysField);

        if (startDate != null && endDate != null && numberOfDays != null) {

            LocalDateTime durationEndDate = startDate.plusDays(numberOfDays);

            return durationEndDate.isBefore(endDate) || durationEndDate.isEqual(endDate);
        }

        return true;
    }
}

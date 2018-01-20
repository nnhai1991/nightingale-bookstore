package com.nightingale.app.model.constraints;

import com.nightingale.app.model.constraints.validator.DayDurationDoesNotExceedDatesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hai on 4/7/2017.
 */
@Constraint(validatedBy = DayDurationDoesNotExceedDatesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DayDurationDoesNotExceedDates {

    String message() default "notChronological";

    String startDateField();

    String endDateField();

    String numberOfDaysField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        DayDurationDoesNotExceedDates[] value();
    }
}

package com.nightingale.app.model.constraints;

import com.nightingale.app.model.constraints.validator.ValidIdentificationNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidIdentificationNumberValidator.class)
public @interface ValidIdentificationNumber {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String identificationTypeId();

    String identificationNumber();

    String message() default "Invalid identification number";


    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidIdentificationNumber[] value();
    }
}

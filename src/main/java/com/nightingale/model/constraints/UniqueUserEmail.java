package com.nightingale.model.constraints;

import com.nightingale.model.constraints.validator.UniqueUserEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueUserEmailValidator.class)
@Target({FIELD, TYPE_USE})
@Retention(RUNTIME)
public @interface UniqueUserEmail {

    String message() default "emailAlreadyInUse";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

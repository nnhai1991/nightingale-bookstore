package com.nightingale.app.model.constraints;

import com.nightingale.app.model.constraints.validator.RequiredImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy=RequiredImageValidator.class)
@Target({ FIELD ,TYPE_USE})
@Retention(RUNTIME)
public @interface RequiredImage {

	String message() default "invalidImage";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @interface List {
        RequiredImage[] value();
    }
}

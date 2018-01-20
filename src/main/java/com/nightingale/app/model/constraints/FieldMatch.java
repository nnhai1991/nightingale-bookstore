package com.nightingale.app.model.constraints;

import com.nightingale.app.model.constraints.validator.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface       FieldMatch {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    String message() default "doesNotMatch";


    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}

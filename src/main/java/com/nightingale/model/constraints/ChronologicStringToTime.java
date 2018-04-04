package com.nightingale.model.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nightingale.model.constraints.validator.ChronologicStringToTimeValidator;

@Constraint(validatedBy = ChronologicStringToTimeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ChronologicStringToTime {

	String message() default "notChronological";

	String startTimeField();

	String endTimeField();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		ChronologicStringToTime[] value();
	}

}

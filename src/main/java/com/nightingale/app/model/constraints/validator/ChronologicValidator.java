package com.nightingale.app.model.constraints.validator;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.nightingale.app.model.constraints.Chronologic;

public class ChronologicValidator implements ConstraintValidator<Chronologic, Object> {

	private String startDateField;
	private String endDateField;

	@Override
	public void initialize(Chronologic constraintAnnotation) {
		this.startDateField = constraintAnnotation.startDateField();
		this.endDateField = constraintAnnotation.endDateField();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		LocalDateTime startDate = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(startDateField);
		LocalDateTime endDate = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(endDateField);

		if (startDate != null && endDate != null) {
			return startDate.isBefore(endDate);
		}

		return true;
	}

}

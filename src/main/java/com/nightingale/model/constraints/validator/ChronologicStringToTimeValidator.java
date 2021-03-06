package com.nightingale.model.constraints.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.nightingale.model.constraints.ChronologicStringToTime;
import com.nightingale.util.DateFormat;

public class ChronologicStringToTimeValidator implements ConstraintValidator<ChronologicStringToTime, Object> {

	private String startTimeField;
	private String endTimeField;

	@Override
	public void initialize(ChronologicStringToTime constraintAnnotation) {

		this.startTimeField = constraintAnnotation.startTimeField();
		this.endTimeField = constraintAnnotation.endTimeField();

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		String startTimeString = (String) new BeanWrapperImpl(value).getPropertyValue(startTimeField);
		String endTimeString = (String) new BeanWrapperImpl(value).getPropertyValue(endTimeField);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat.DISPLAY_TIME);

		try {
			LocalTime startTime = LocalTime.parse(startTimeString, formatter);
			LocalTime endTime = LocalTime.parse(endTimeString, formatter);
			
			return startTime.isBefore(endTime);
		} catch (DateTimeParseException  | NullPointerException exception) {
			return true;
		}

	}

}

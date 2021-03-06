package com.nightingale.model.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nightingale.model.constraints.UniqueUserEmail;
import com.nightingale.repository.UserRepository;

@Component
public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

	@Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            return userRepository.findByEmail(value) == null;
    }

	@Override
	public void initialize(UniqueUserEmail constraintAnnotation) {
	}
}

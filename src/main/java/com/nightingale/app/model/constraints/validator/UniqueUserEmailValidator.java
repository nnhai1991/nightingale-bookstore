package com.nightingale.app.model.constraints.validator;

import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.constraints.UniqueUserEmail;
import com.nightingale.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private UserRepository userRepository;

    @Autowired
    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            return userRepository.findByEmail(value) == null;
    }
}

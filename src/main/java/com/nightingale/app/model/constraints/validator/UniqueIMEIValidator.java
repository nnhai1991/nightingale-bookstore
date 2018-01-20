//package com.nightingale.app.model.constraints.validator;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.nightingale.app.model.constraints.UniqueIMEI;
//import com.nightingale.app.repository.IMEIInfoRepository;
//
//@Component
//public class UniqueIMEIValidator implements ConstraintValidator<UniqueIMEI, String> {
//
//    private IMEIInfoRepository imeiInfoRepository;
//
//    @Autowired
//    public UniqueIMEIValidator(IMEIInfoRepository imeiInfoRepository) {
//        this.imeiInfoRepository = imeiInfoRepository;
//
//    }
//
//    @Override
//    public void initialize(UniqueIMEI constraintAnnotation) {
//
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//
//        return imeiInfoRepository.readByIMEI(value) == null;
//
//    }
//}

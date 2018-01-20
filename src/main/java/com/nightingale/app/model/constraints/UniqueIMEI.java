//package com.nightingale.app.model.constraints;
//
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.ElementType.TYPE_USE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//
//import com.nightingale.app.model.constraints.validator.UniqueIMEIValidator;
//
//@Constraint(validatedBy = UniqueIMEIValidator.class)
//@Target({FIELD, TYPE_USE})
//@Retention(RUNTIME)
//public @interface UniqueIMEI {
//
//    String message() default "imeiExist";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//
//
//}

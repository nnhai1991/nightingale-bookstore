package com.nightingale.app.model.constraints.validator;

import com.nightingale.app.model.constraints.RequiredImage;
import com.nightingale.web.util.UtilValidation;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RequiredImageValidator implements ConstraintValidator<RequiredImage, MultipartFile> {

    @Override
    public void initialize(RequiredImage constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        //return valid if image is empty
        if (!UtilValidation.isFileNotEmpty(value))
            return true;

        boolean valid = false;

        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(value.getInputStream());
            valid = bufferedImage != null;

            if (bufferedImage != null) {
                bufferedImage.flush();
                bufferedImage = null;
            }
        } catch (IOException e) {
            return false;
        }

        return valid;
    }

}

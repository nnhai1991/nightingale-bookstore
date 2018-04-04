package com.nightingale.model.dto;

import com.nightingale.model.constraints.FieldMatch;
import com.nightingale.util.web.UtilValidation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 
 * @author Hai
 *
 */
@Data
@FieldMatch.List({@FieldMatch(first = "password", second = "passwordConfirm")})
public class UserForUpdatePassword implements Serializable {

    private static final long serialVersionUID = 1737106297510777970L;
    private Integer id;

    private String email;

    @Pattern(regexp = UtilValidation.MIN8LENGTH_MIN1LOWERCASE_MIN1UPPERCASE_MIN1DIGIT_MIN1SPECIALCHAR)
    private String password;

    @NotNull
    private String passwordConfirm;

    private String updatedBy;
}

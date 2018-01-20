package com.nightingale.app.model.dto;

import com.nightingale.app.model.constraints.FieldMatch;
import com.nightingale.web.util.UtilValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * -------------------------------------------------
 * <p>
 * author - youngsam.byun
 * <p>
 * -------------------------------------------------
 */

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

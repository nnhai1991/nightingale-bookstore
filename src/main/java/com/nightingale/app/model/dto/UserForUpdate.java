package com.nightingale.app.model.dto;

import com.nightingale.web.util.UtilValidation;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * -------------------------------------------------
 * <p>
 * author - youngsam.byun
 * <p>
 * -------------------------------------------------
 */
public class UserForUpdate implements Serializable {

    private static final long serialVersionUID = -4345829692414517266L;
    private Integer id;

    private String email;

    @NotNull
    private Integer roleId;

    @NotEmpty
    @Pattern(regexp = UtilValidation.ALPHABET_WITH_DOT_SPACE)
    private String firstName;

    @NotEmpty
    @Pattern(regexp = UtilValidation.ALPHABET_WITH_DOT_SPACE)
    private String lastName;

    private Boolean enabled;
    private Boolean notLocked;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;


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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getNotLocked() {
        return notLocked;
    }

    public void setNotLocked(Boolean notLocked) {
        this.notLocked = notLocked;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

}

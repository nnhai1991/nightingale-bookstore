package com.nightingale.model.dto;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

import com.nightingale.util.web.UtilValidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author Hai
 *
 */
@Data
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
}

package com.nightingale.app.entity;

import com.nightingale.app.model.constraints.FieldMatch;
import com.nightingale.app.model.constraints.UniqueUserEmail;
import com.nightingale.web.util.UtilValidation;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * -------------------------------------------------
 * <p>
 * author - youngsam.byun
 * <p>
 * -------------------------------------------------
 */
@Entity
@Table(name = "dbo.[User]")
@FieldMatch.List({ @FieldMatch(first = "password", second = "passwordConfirm") })
public class User implements Serializable {

	private static final long serialVersionUID = -5379792979026217998L;

	@Id
	private Integer id;

	@NotNull
	private Integer roleId;

	@NotEmpty
	@UniqueUserEmail
	private String email;

	@Pattern(regexp = UtilValidation.MIN8LENGTH_MIN1LOWERCASE_MIN1UPPERCASE_MIN1DIGIT_MIN1SPECIALCHAR)
	private String password;

	@NotEmpty
	@Pattern(regexp = UtilValidation.ALPHA_NUMERIC_WITH_SPACE)
	private String firstName;

	@NotEmpty
	@Pattern(regexp = UtilValidation.ALPHA_NUMERIC_WITH_SPACE)
	private String lastName;
	private Integer failedLoginAttempt;

	private Boolean enabled;
	private Boolean notLocked;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

	@Transient
	private String fullName;
	@NotNull
	@Transient
	private String passwordConfirm;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public Integer getFailedLoginAttempt() {
		return failedLoginAttempt;
	}

	public void setFailedLoginAttempt(Integer failedLoginAttempt) {
		this.failedLoginAttempt = failedLoginAttempt;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

	@PrePersist
	protected void onCreate() {
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

}

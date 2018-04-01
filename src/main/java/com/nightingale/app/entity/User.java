package com.nightingale.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.nightingale.web.util.UtilValidation;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * -------------------------------------------------
 * <p>
 * author - nn.hai
 * <p>
 * -------------------------------------------------
 */
@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -5379792979026217998L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Integer roleId;

	@NotEmpty
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

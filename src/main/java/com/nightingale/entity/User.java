package com.nightingale.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.nightingale.util.web.UtilValidation;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class User extends BaseEntity {

	private static final long serialVersionUID = -5379792979026217998L;

	@ManyToOne
	private Role role;

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
	
	private int failedLoginAttempt;

	private boolean enabled;
	
	@NotNull
	private String timezone;
	
	private String facebookId;
	
	@OneToOne
	private Customer customer;
}

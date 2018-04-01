package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hai
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Article extends BaseEntity {

	private static final long serialVersionUID = 4291701426443797243L;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String code;

	private String description;

	private float price;
	
	private int priority;

	@NotNull
	private Boolean enabled;
}

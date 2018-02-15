package com.nightingale.app.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hai
 *
 */
@Entity
public class Article extends BaseEntity {

	private static final long serialVersionUID = 4291701426443797243L;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String code;

	private String description;

	private BigDecimal price;
	
	private int priority;

	@NotNull
	private Boolean enabled;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}

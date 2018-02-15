package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class TagType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3232971949486047180L;

	@NotEmpty
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

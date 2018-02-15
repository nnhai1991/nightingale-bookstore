package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class Tag extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2607491916971984183L;
	@NotEmpty	
	private String name;
	
	private Integer tagTypeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTagTypeId() {
		return tagTypeId;
	}

	public void setTagTypeId(Integer tagTypeId) {
		this.tagTypeId = tagTypeId;
	}
	
	

}

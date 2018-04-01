package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TagType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3232971949486047180L;

	@NotEmpty
	private String name;
}

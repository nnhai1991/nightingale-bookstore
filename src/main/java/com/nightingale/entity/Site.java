package com.nightingale.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public class Site extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4677905766805980035L;
	
	@NotNull
	private String name;
	
	@NotNull
	private String type;
	
	private String address;
	
	private boolean enabled;
}

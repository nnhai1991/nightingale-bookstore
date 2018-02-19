package com.nightingale.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BaseDTO<E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5873432504271521688L;
	
	@Valid
	@NotNull
	private E entity;

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}
	
	
}

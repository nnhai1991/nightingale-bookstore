package com.nightingale.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public class Stock extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6960439458185071526L;

	@ManyToOne
	private Article article;
	
	@ManyToOne
	private Site fromSite;
	
	@ManyToOne
	private Site toSite;
		
	private int quantity;
	
	private String type;
}

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
public class Discount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133457586537741132L;
	
	@ManyToOne
	private Article article;
	
	private int amount;
	
	private String type;
	
	

}

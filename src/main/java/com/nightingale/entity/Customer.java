package com.nightingale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 
 * @author nn.hai
 *
 */

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5430826947230793939L;
	
	private String name;
	
	@Column(unique=true)
	private String email;
	
	private String phoneNo;
	
	private String shippingName;
	
	private String shippingPhoneNo;
	
	private String shippingAddress;
}

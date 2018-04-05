package com.nightingale.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.nightingale.util.DateFormat;

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
	
	private Timestamp stockDate;
	
	@Transient
	@DateTimeFormat(pattern=DateFormat.DISPLAY_DATE_TIME)
	private LocalDateTime displayStockDateTime;
}

package com.nightingale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2607491916971984183L;
	
	@Id
	private String name;
	
	@NonNull
	private String tagType;
}

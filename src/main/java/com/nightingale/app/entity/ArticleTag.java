package com.nightingale.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ArticleTag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6288255644353343530L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@NotNull
	private Integer articleId;
	
	@NotNull
	private String tag;

}

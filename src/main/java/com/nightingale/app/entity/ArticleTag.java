package com.nightingale.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
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
	private Integer tagId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	
}

package com.nightingale.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hai
 *
 */
@Entity
public class ArticleImage implements Serializable {

	private static final long serialVersionUID = 4291701426443797243L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Integer articleId;

	@NotNull
	private Integer assetId;

	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

	@PrePersist
	protected void onCreate() {
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

	public LocalDateTime getCreatedDateLocal() {
		return createdDate.toLocalDateTime();
	}
}

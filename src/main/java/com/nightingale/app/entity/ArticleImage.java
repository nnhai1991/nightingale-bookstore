package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

/**
 * @author hai
 *
 */
@Entity
public class ArticleImage extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7330242016333653993L;
	
	private Integer sequence = 0;

	@NotNull
	private Integer articleId;

	@NotNull
	private Integer assetId;

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
}

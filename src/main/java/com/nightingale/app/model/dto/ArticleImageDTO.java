package com.nightingale.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.model.constraints.RequiredImage;

/**
 * @author hai
 *
 */
public class ArticleImageDTO implements Serializable {

	private static final long serialVersionUID = -4951262262794080160L;
	
	@NotNull
	private Integer sequence = 0;
	
	@NotNull
	private Integer articleId;

	@RequiredImage
	private MultipartFile image;
	
	private Integer articleImageId;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleImageId() {
		return articleImageId;
	}

	public void setArticleImageId(Integer articleImageId) {
		this.articleImageId = articleImageId;
	}
	
	
	
}

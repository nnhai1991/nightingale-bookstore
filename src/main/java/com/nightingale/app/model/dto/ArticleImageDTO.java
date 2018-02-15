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

	private Article article; 
	
	@NotNull
	@Valid
	private ArticleImage articleImage;

	@RequiredImage
	private MultipartFile image;

	public ArticleImage getArticleImage() {
		return articleImage;
	}

	public void setArticleImage(ArticleImage articleImage) {
		this.articleImage = articleImage;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}

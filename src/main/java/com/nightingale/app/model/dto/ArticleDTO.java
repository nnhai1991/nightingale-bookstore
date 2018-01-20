package com.nightingale.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nightingale.app.entity.Article;
import com.nightingale.app.model.constraints.RequiredImage;

/**
 * @author hai
 *
 */
public class ArticleDTO implements Serializable {

	private static final long serialVersionUID = -4951262262794080160L;

	@NotNull
	@Valid
	private Article article;

	@RequiredImage
	private MultipartFile image;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}

package com.nightingale.app.model.dto;

import java.util.List;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;

public class ArticleDTO {
	private Article article;
	private List<ArticleImage> articleImages;
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<ArticleImage> getArticleImages() {
		return articleImages;
	}
	public void setArticleImages(List<ArticleImage> articleImages) {
		this.articleImages = articleImages;
	}
	
	
}

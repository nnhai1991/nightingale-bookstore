package com.nightingale.app.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.entity.Tag;

import lombok.Data;

@Data
public class ArticleDTO {
	private Article article;
	private List<ArticleImage> articleImages;
	private List<String> tags;

	public ArticleDTO() {
		this.article = new Article();
		this.articleImages = new ArrayList<>();
		this.tags = new ArrayList<>();
	}
}

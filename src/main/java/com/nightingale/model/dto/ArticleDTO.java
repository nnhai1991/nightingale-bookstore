package com.nightingale.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.nightingale.entity.Article;
import com.nightingale.entity.ArticleImage;

import lombok.Data;

@Data
public class ArticleDTO {
	private Article article;
	private List<ArticleImage> articleImages;
	private List<String> tags;
	private List<String> categories;
	private List<String> authors;
	private List<String> conditions;
	
	public ArticleDTO() {
		this.article = new Article();
		this.articleImages = new ArrayList<>();
		this.tags = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.authors = new ArrayList<>();
		this.conditions = new ArrayList<>();
	}
}

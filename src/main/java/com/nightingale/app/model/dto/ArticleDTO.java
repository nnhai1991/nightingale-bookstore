package com.nightingale.app.model.dto;

import java.util.List;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;

import lombok.Data;


@Data
public class ArticleDTO {
	private Article article;
	private List<ArticleImage> articleImages;
		
}

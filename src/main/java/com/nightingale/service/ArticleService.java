package com.nightingale.service;

import java.util.List;

import com.nightingale.entity.Article;
import com.nightingale.entity.ArticleImage;
import com.nightingale.model.dto.ArticleDTO;
import com.nightingale.model.dto.ArticleImageDTO;

public interface ArticleService extends BaseService<Article> {

	ArticleDTO readDTO(Integer articleId);

	Article getByCode(String code);

	List<Article> findActiveArticleList();
	
	Boolean createArticleImageDTO(ArticleImageDTO articleDTO);

	Boolean updateArticleImageDTO(ArticleImageDTO articleDTO) ;

	List<ArticleImage> findArticleImageByArticleId(int articleId);

	ArticleImage readArticleImage(Integer articleImageId);

	void deleteArticleImage(Integer articleImageId);

	void create(ArticleDTO articleDTO);

	void update(ArticleDTO articleDto);

}

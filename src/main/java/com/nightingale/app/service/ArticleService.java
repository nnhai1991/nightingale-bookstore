package com.nightingale.app.service;

import java.util.List;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.model.dto.ArticleImageDTO;

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

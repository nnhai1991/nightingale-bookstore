package com.nightingale.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.Article;
import com.nightingale.entity.ArticleImage;
import com.nightingale.model.dto.ArticleDTO;
import com.nightingale.model.dto.ArticleImageDTO;

public interface ArticleService {

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

	Pair<List<Article>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize);

	void delete(Integer id);

}

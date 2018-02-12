package com.nightingale.app.service;

import java.util.List;

import com.nightingale.app.entity.Article;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.ArticleDTO;

public interface ArticleService extends BaseService<Article> {

	ArticleDTO readDTO(Integer articleId) throws ObjectNotFoundException;

	Article getByCode(String code);

	List<Article> findActiveArticleList();
}

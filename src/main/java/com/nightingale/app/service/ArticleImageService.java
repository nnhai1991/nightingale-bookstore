package com.nightingale.app.service;

import java.util.List;

import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.ArticleImageDTO;

public interface ArticleImageService {

	ArticleImageDTO readDTO(Integer articleId) throws ObjectNotFoundException;

	Boolean createDTO(ArticleImageDTO articleDTO);

	Boolean updateDTO(ArticleImageDTO articleDTO) throws ObjectNotFoundException;

	List<ArticleImage> findArticleImageByArticleId(int articleId);
}

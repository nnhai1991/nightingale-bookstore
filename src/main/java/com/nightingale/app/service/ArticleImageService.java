package com.nightingale.app.service;

import java.util.List;

import com.nightingale.app.entity.ArticleImage;

import com.nightingale.app.model.dto.ArticleImageDTO;

public interface ArticleImageService extends BaseService<ArticleImage>{

	Boolean createDTO(ArticleImageDTO articleDTO);

	Boolean updateDTO(ArticleImageDTO articleDTO) ;

	List<ArticleImage> findArticleImageByArticleId(int articleId);
}

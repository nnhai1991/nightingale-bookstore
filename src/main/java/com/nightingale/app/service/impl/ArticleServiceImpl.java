package com.nightingale.app.service.impl;

import com.nightingale.app.entity.Article;
import com.nightingale.app.exception.ObjectCreationException;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.repository.*;
import com.nightingale.app.service.ArticleService;
import com.nightingale.app.service.AssetService;
import com.nightingale.web.util.UtilValidation;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private AssetService assetService;

	@Value("${asset.upload.path}")
	private String uploadpath;

	@Override
	@Transactional
	public Article create(Article article) {

		if (article != null) {
			try {
				article.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				return articleRepository.save(article);
			} catch (DataIntegrityViolationException exception) {
				throw new ObjectCreationException(exception.getStackTrace(), "create Article", "",
						"Failed to create Article", article);
			}
		}

		return null;
	}

	@Override
	public Article read(Integer articleId) {
		if (UtilValidation.isValidId(articleId))
			return articleRepository.findOne(articleId);
		return null;
	}

	@Override
	@Transactional
	public Article update(Article article) {
		if (article != null) {
			try {
				article.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				return articleRepository.save(article);
			} catch (DataIntegrityViolationException exception) {
				throw new ObjectCreationException(exception.getStackTrace(), "update Article", "",
						"Failed to update Article", article);
			}
		}

		return null;
	}

	@Override
	public void delete(Integer articleId) {

		if (UtilValidation.isValidId(articleId))
			articleRepository.delete(articleId);
	}

	@Override
	public List<Article> getListAll() {
		return articleRepository.findAll();
	}

	@Override
	public Pair<List<Article>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo,
			Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Article> result;
			if (UtilValidation.isValidString(keyword)) {
				result = articleRepository.findBySearch(keyword, pageRequest);
			} else {
				result = articleRepository.findAll(pageRequest);
			}
			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

	@Override
	@Transactional
	public ArticleDTO readDTO(Integer articleId) throws ObjectNotFoundException {

		if (UtilValidation.isValidId(articleId)) {

			Article article = articleRepository.findOne(articleId);

			if (article != null) {

				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setArticle(article);
				return articleDTO;

			}

		}

		return null;
	}

	@Override
	@Transactional
	public Boolean createDTO(ArticleDTO articleDTO) {

		if (articleDTO != null) {

			if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {

				Integer assetId = assetService.create(articleDTO.getImage(), uploadpath);

				if (assetId > 0) {

					articleDTO.getArticle().setAssetId(assetId);

					try {
						articleDTO.getArticle()
								.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
						return articleRepository.save(articleDTO.getArticle()) != null;
					} catch (DataIntegrityViolationException exception) {
						exception.printStackTrace();
						throw new ObjectCreationException(exception.getStackTrace(), "create ArticleDTO", "",
								"Failed to create Article", articleDTO.getArticle());
					}
				} else {
					throw new ObjectCreationException(new StackTraceElement[0], "create ArticleDTO", "",
							"Failed to create Article Image", articleDTO.getImage());
				}
			}

		}

		return false;
	}

	@Override
	@Transactional
	public Boolean updateDTO(ArticleDTO articleDTO) throws ObjectNotFoundException {

		if (articleDTO == null)
			return false;

		Article articleFromDB = articleRepository.findOne(articleDTO.getArticle().getId());

		Integer assetId = articleFromDB.getAssetId();

		if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {
			assetService.delete(assetId, uploadpath);
			assetId = assetService.create(articleDTO.getImage(), uploadpath);
		}

		if (assetId <= 0)
			throw new ObjectCreationException(new StackTraceElement[0], "updateDTO ArticleDTO", "",
					"Failed to Update Image", articleDTO.getImage());

		articleDTO.getArticle().setAssetId(assetId);
		try {
			articleDTO.getArticle().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			return articleRepository.save(articleDTO.getArticle()) != null;
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ObjectCreationException(exception.getStackTrace(), "updateDTO ArticleDTO", "",
					"Failed to update Article", articleDTO.getArticle());
		}

	}

	@Override
	public Article getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findActiveArticleList() {
		// TODO Auto-generated method stub
		return null;
	}
}

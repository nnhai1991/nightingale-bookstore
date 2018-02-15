package com.nightingale.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nightingale.app.entity.Article;
import com.nightingale.app.exception.ObjectCreationException;

import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.repository.ArticleImageRepository;
import com.nightingale.app.repository.ArticleRepository;
import com.nightingale.app.service.ArticleService;
import com.nightingale.web.util.UtilValidation;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleImageRepository articleImageRepository;

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
	public ArticleDTO readDTO(Integer articleId)  {

		if (UtilValidation.isValidId(articleId) == false)
			return null;
		Article article = articleRepository.findOne(articleId);

		if (article == null)
			return null;

		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setArticle(article);
		articleDTO.setArticleImages(articleImageRepository.findByArticleId(articleId));
		return articleDTO;
	}

	@Override
	public Article getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findActiveArticleList() {
		return articleRepository.findByEnabled(true);
	}
}

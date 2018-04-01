package com.nightingale.app.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nightingale.app.entity.Article;
import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.entity.ArticleTag;
import com.nightingale.app.entity.Tag;
import com.nightingale.app.exception.NightingaleException;

import com.nightingale.app.model.dto.ArticleDTO;
import com.nightingale.app.model.dto.ArticleImageDTO;
import com.nightingale.app.repository.ArticleImageRepository;
import com.nightingale.app.repository.ArticleRepository;
import com.nightingale.app.repository.ArticleTagRepository;
import com.nightingale.app.repository.TagRepository;
import com.nightingale.app.service.ArticleService;
import com.nightingale.app.service.AssetService;
import com.nightingale.app.service.TagService;
import com.nightingale.web.util.UtilValidation;

@Service
public class ArticleServiceImpl implements ArticleService {

	static final String CACHE_NAME = "Article";

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleImageRepository articleImageRepository;

	@Autowired
	private ArticleTagRepository articleTagRepository;

	@Autowired
	private TagService tagService;

	@Autowired
	private AssetService assetService;

	@Value("${asset.upload.path}")
	private String uploadpath;

	@Value("${asset.root.driver}")
	private String rootDriver;

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Article create(Article article) {

		if (article != null) {
			try {
				article.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				return articleRepository.save(article);
			} catch (DataIntegrityViolationException exception) {
				throw new NightingaleException(exception.getStackTrace(), "create Article", "",
						"Failed to create Article", article);
			}
		}

		return null;
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Article read(Integer articleId) {
		if (UtilValidation.isValidId(articleId))
			return articleRepository.findOne(articleId);
		return null;
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Article update(Article article) {
		if (article != null) {
			try {
				article.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
				return articleRepository.save(article);
			} catch (DataIntegrityViolationException exception) {
				throw new NightingaleException(exception.getStackTrace(), "update Article", "",
						"Failed to update Article", article);
			}
		}

		return null;
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void delete(Integer articleId) {

		if (UtilValidation.isValidId(articleId))
			articleRepository.delete(articleId);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public List<Article> getListAll() {
		return articleRepository.findAll();
	}

	@Override
	@Cacheable(CACHE_NAME)
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
	@Cacheable(CACHE_NAME)
	public ArticleDTO readDTO(Integer articleId) {

		if (UtilValidation.isValidId(articleId) == false)
			return null;
		Article article = articleRepository.findOne(articleId);

		if (article == null)
			return null;

		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setArticle(article);
		articleDTO.setArticleImages(articleImageRepository.findByArticleId(articleId));
		articleDTO.setTags(articleTagRepository.findByArticleId(articleId).stream().map(ar -> ar.getTag())
				.collect(Collectors.toList()));
		return articleDTO;
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Article getByCode(String code) {
		return articleRepository.findByCode(code);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public List<Article> findActiveArticleList() {
		return articleRepository.findByEnabled(true);
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Boolean createArticleImageDTO(ArticleImageDTO articleDTO) {

		if (articleDTO != null) {

			if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {

				Integer assetId = assetService.create(articleDTO.getImage(), rootDriver + File.separator + uploadpath);

				if (assetId > 0) {
					ArticleImage articleImage = new ArticleImage();
					articleImage.setArticleId(articleDTO.getArticleId());
					articleImage.setAssetId(assetId);
					articleImage.setSequence(articleDTO.getSequence());
					try {
						articleImage.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
						return articleImageRepository.save(articleImage) != null;
					} catch (DataIntegrityViolationException exception) {
						exception.printStackTrace();
						throw new NightingaleException(exception.getStackTrace(), "create ArticleImageDTO", "",
								"Failed to create Article", articleImage);
					}
				} else {
					throw new NightingaleException(new StackTraceElement[0], "create ArticleImageDTO", "",
							"Failed to create Article Image", articleDTO.getImage());
				}
			}

		}

		return false;
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Boolean updateArticleImageDTO(ArticleImageDTO articleDTO) {

		if (articleDTO == null || articleDTO.getArticleImageId() == null)
			return false;

		ArticleImage articleImgFromDB = articleImageRepository.findOne(articleDTO.getArticleImageId());

		Integer assetId = articleImgFromDB.getAssetId();

		if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {
			assetService.delete(assetId, rootDriver + File.separator + uploadpath);
			assetId = assetService.create(articleDTO.getImage(), rootDriver + File.separator + uploadpath);
		}

		if (assetId <= 0)
			throw new NightingaleException(new StackTraceElement[0], "updateDTO ArticleImageDTO", "",
					"Failed to Update Image", articleDTO.getImage());

		articleImgFromDB.setAssetId(assetId);
		articleImgFromDB.setSequence(articleDTO.getSequence());
		try {
			articleImgFromDB.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			return articleImageRepository.save(articleImgFromDB) != null;
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new NightingaleException(exception.getStackTrace(), "updateDTO ArticleImageDTO", "",
					"Failed to update Article", articleDTO);
		}

	}

	@Override
	@Cacheable(CACHE_NAME)
	public List<ArticleImage> findArticleImageByArticleId(int articleId) {
		return articleImageRepository.findByArticleId(articleId);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public ArticleImage readArticleImage(Integer articleImageId) {
		return articleImageRepository.findOne(articleImageId);
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void deleteArticleImage(Integer articleImageId) {
		ArticleImage entity = readArticleImage(articleImageId);
		if (assetService.delete(entity.getAssetId(), rootDriver + File.separator + uploadpath))
			articleImageRepository.delete(articleImageId);

	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void create(ArticleDTO articleDTO) {
		Article article = this.create(articleDTO.getArticle());
		for (String tag : articleDTO.getTags()) {
			Tag tagObj = tagService.read(tag);
			if (tagObj == null)
				tagService.create(tag);
			ArticleTag articleTag = new ArticleTag();
			articleTag.setArticleId(article.getId());
			articleTag.setTag(tag);
			articleTagRepository.save(articleTag);
		}
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void update(ArticleDTO articleDTO) {
		Article article = this.update(articleDTO.getArticle());
		for(ArticleTag existing : articleTagRepository.findByArticleId(article.getId())) {
			articleTagRepository.delete(existing);
		}
		
		for (String tag : articleDTO.getTags()) {
			Tag tagObj = tagService.read(tag);
			if (tagObj == null)
				tagService.create(tag);
			ArticleTag articleTag = new ArticleTag();
			articleTag.setArticleId(article.getId());
			articleTag.setTag(tag);
			articleTagRepository.save(articleTag);
		}
	}
}

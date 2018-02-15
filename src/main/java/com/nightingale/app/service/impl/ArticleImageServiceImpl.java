package com.nightingale.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nightingale.app.entity.ArticleImage;
import com.nightingale.app.exception.ObjectCreationException;

import com.nightingale.app.model.dto.ArticleImageDTO;
import com.nightingale.app.repository.ArticleImageRepository;
import com.nightingale.app.service.ArticleImageService;
import com.nightingale.app.service.AssetService;
import com.nightingale.web.util.UtilValidation;
import com.nightingale.app.repository.ArticleImageRepository;
import com.nightingale.app.repository.ArticleRepository;

@Service
public class ArticleImageServiceImpl implements ArticleImageService {

	@Autowired
	private ArticleImageRepository articleImageRepository;

	@Autowired
	private AssetService assetService;

	@Value("${asset.upload.path}")
	private String uploadpath;


	@Override
	@Transactional
	public ArticleImageDTO readDTO(Integer articleId)  {

		if (UtilValidation.isValidId(articleId)) {

			ArticleImage articleImg = articleImageRepository.findOne(articleId);

			if (articleImg != null) {

				ArticleImageDTO articleDTO = new ArticleImageDTO();
				articleDTO.setArticleImage(articleImg);
				return articleDTO;

			}

		}

		return null;
	}

	@Override
	@Transactional
	public Boolean createDTO(ArticleImageDTO articleDTO) {

		if (articleDTO != null) {

			if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {

				Integer assetId = assetService.create(articleDTO.getImage(), uploadpath);

				if (assetId > 0) {

					articleDTO.getArticleImage().setAssetId(assetId);

					try {
						articleDTO.getArticleImage()
								.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
						return articleImageRepository.save(articleDTO.getArticleImage()) != null;
					} catch (DataIntegrityViolationException exception) {
						exception.printStackTrace();
						throw new ObjectCreationException(exception.getStackTrace(), "create ArticleImageDTO", "",
								"Failed to create Article", articleDTO.getArticleImage());
					}
				} else {
					throw new ObjectCreationException(new StackTraceElement[0], "create ArticleImageDTO", "",
							"Failed to create Article Image", articleDTO.getImage());
				}
			}

		}

		return false;
	}

	@Override
	@Transactional
	public Boolean updateDTO(ArticleImageDTO articleDTO)  {

		if (articleDTO == null)
			return false;

		ArticleImage articleImgFromDB = articleImageRepository.findOne(articleDTO.getArticleImage().getId());

		Integer assetId = articleImgFromDB.getAssetId();

		if (UtilValidation.isFileNotEmpty(articleDTO.getImage())) {
			assetService.delete(assetId, uploadpath);
			assetId = assetService.create(articleDTO.getImage(), uploadpath);
		}

		if (assetId <= 0)
			throw new ObjectCreationException(new StackTraceElement[0], "updateDTO ArticleImageDTO", "",
					"Failed to Update Image", articleDTO.getImage());

		articleDTO.getArticleImage().setAssetId(assetId);
		try {
			articleDTO.getArticleImage().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			return articleImageRepository.save(articleDTO.getArticleImage()) != null;
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ObjectCreationException(exception.getStackTrace(), "updateDTO ArticleImageDTO", "",
					"Failed to update Article", articleDTO.getArticleImage());
		}

	}

	@Override
	public List<ArticleImage> findArticleImageByArticleId(int articleId) {
		return articleImageRepository.findByArticleId(articleId);
	}
	
	private ArticleImageRepository campaignImageRepository;
    private ArticleRepository campaignRepository;

    @Override
    @Transactional
    public ArticleImage create(ArticleImage campaignImage) {

        if (campaignImage != null) {
        	return campaignImageRepository.save(campaignImage);
        }

        return null;
    }

    @Override
    public ArticleImage read(Integer campaignImageId) {
        if (UtilValidation.isValidId(campaignImageId))
            return campaignImageRepository.findOne(campaignImageId);

        return null;
    }

    @Override
    @Transactional
    public ArticleImage update(ArticleImage campaignImage) {
        if (campaignImage != null) {
               return campaignImageRepository.save(campaignImage);
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Integer campaignImageId) {

        if (UtilValidation.isValidId(campaignImageId))
        	campaignImageRepository.delete(campaignImageId);

    }

    @Override
    public List<ArticleImage> getListAll() {
        return campaignImageRepository.findAll();
    }

    @Override
    public Pair<List<ArticleImage>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo,
                                                                            Integer pageSize) {
        return Pair.of(new ArrayList<>(), 0);
    }
}

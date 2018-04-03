package com.nightingale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nightingale.entity.Tag;
import com.nightingale.exception.NightingaleException;
import com.nightingale.repository.TagRepository;
import com.nightingale.service.TagService;
import com.nightingale.util.web.UtilValidation;

@Service
public class TagServiceImp implements TagService {

	static final String CACHE_NAME = "Tag";

	@Autowired
	private TagRepository tagRepository;

	@Value("${asset.upload.path}")
	private String uploadpath;

	@Override
	@Cacheable(CACHE_NAME)
	public Tag read(String tag) {
		if (UtilValidation.isValidString(tag)){
			return tagRepository.findOne(tag);
		}
		return null;
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void delete(String tagId) {

		if (UtilValidation.isValidString(tagId)){
			tagRepository.delete(tagId);
		}
	}

	@Override
	@Cacheable(CACHE_NAME)
	public List<Tag> getListAll() {
		return tagRepository.findAll();
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Pair<List<Tag>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Tag> result;
			// if (UtilValidation.isValidString(keyword)) {
			// result = tagRepository.findBySearch(keyword, pageRequest);
			// } else {
			result = tagRepository.findAll(pageRequest);
			// }
			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

	@Override
	@Transactional
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Tag create(String tag, String tagType) {
		if (tag != null) {
			try {
				Tag tagObj = new Tag();
				tagObj.setName(tag);
				tagObj.setTagType(tagType);
				return tagRepository.save(tagObj);
			} catch (DataIntegrityViolationException exception) {
				throw new NightingaleException(exception.getStackTrace(), "create Tag", "", "Failed to create Tag",
						tag);
			}
		}

		return null;
	}

	@Override
	@Cacheable(CACHE_NAME)
	public List<Tag> getTagsByTagType(String tagType) {
		return tagRepository.findByTagType(tagType);
	}
}

package com.nightingale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nightingale.entity.Site;
import com.nightingale.repository.SiteRepository;
import com.nightingale.service.SiteService;
import com.nightingale.util.web.UtilValidation;

@Service
public class SiteServiceImp implements SiteService {

	static final String CACHE_NAME = "Site";

	@Autowired
	private SiteRepository siteRepository;

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Site create(Site site) {
		site.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return siteRepository.save(site);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Site read(int tId) {
		return siteRepository.findOne(tId);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Site update(Site site) {
		site.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return siteRepository.save(site);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void delete(int tId) {
		siteRepository.delete(tId);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Pair<List<Site>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Site> result;
			if (UtilValidation.isValidString(keyword)) {
				result = siteRepository.findBySearch(keyword, pageRequest);
			} else {
				result = siteRepository.findAll(pageRequest);
			}
			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

}

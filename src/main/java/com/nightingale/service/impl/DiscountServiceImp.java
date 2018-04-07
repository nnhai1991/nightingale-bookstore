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

import com.nightingale.entity.Discount;
import com.nightingale.repository.DiscountRepository;
import com.nightingale.service.ArticleService;
import com.nightingale.service.DiscountService;
import com.nightingale.util.web.UtilValidation;

@Service
public class DiscountServiceImp implements DiscountService {

	static final String CACHE_NAME = "Discount";

	@Autowired
	private DiscountRepository discountRepository;
	@Autowired
	private ArticleService articleService;

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Discount create(Discount discount) {
		discount.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

		return discountRepository.save(discount);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Discount read(int tId) {
		Discount discount = discountRepository.findOne(tId);
		
		if (discount == null)
			return null;
		
		discount.convertToLocalTime();
		return discount;
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Discount update(Discount discount) {
		discount.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return discountRepository.save(discount);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void delete(int tId) {
		discountRepository.delete(tId);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Pair<List<Discount>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo,
			Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Discount> result;
			if (UtilValidation.isValidString(keyword)) {
				result = discountRepository.findBySearch(keyword, pageRequest);
			} else {
				result = discountRepository.findAll(pageRequest);
			}

			for (Discount discount : result.getContent()) {
				discount.convertToLocalTime();
			}

			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

}

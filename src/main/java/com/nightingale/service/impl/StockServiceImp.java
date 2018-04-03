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

import com.nightingale.entity.Stock;
import com.nightingale.repository.StockRepository;
import com.nightingale.service.StockService;
import com.nightingale.util.web.UtilValidation;

@Service
public class StockServiceImp implements StockService {

	static final String CACHE_NAME = "Stock";

	@Autowired
	private StockRepository stockRepository;

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Stock create(Stock stock) {
		stock.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return stockRepository.save(stock);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Stock read(int tId) {
		return stockRepository.findOne(tId);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Stock update(Stock stock) {
		stock.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return stockRepository.save(stock);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void delete(int tId) {
		stockRepository.delete(tId);
	}

	@Override
	@Cacheable(CACHE_NAME)
	public Pair<List<Stock>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) {
		if (pageSize > 0) {
			PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
			Page<Stock> result;
			if (UtilValidation.isValidString(keyword)) {
				result = stockRepository.findBySearch(keyword, pageRequest);
			} else {
				result = stockRepository.findAll(pageRequest);
			}
			return Pair.of(result.getContent(), (int) result.getTotalElements());
		}
		return Pair.of(new ArrayList<>(), 0);
	}

}

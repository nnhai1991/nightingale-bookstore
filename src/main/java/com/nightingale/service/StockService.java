package com.nightingale.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.Stock;

public interface StockService {

	Stock create(Stock t);

	Stock read(int tId);

	Stock update(Stock t);

	void delete(int tId);

	Pair<List<Stock>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize);
}

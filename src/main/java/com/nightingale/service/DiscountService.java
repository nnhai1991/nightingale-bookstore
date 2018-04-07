package com.nightingale.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.Discount;

public interface DiscountService {

	Discount create(Discount t);

	Discount read(int tId);

	Discount update(Discount t);

	void delete(int tId);

	Pair<List<Discount>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize);
}

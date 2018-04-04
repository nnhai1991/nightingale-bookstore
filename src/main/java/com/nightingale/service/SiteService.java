package com.nightingale.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.Site;

public interface SiteService {

	Site create(Site t);

	Site read(int tId);

	Site update(Site t);

	void delete(int tId);

	Pair<List<Site>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize);

	List<Site> getAll();
}

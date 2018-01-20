package com.nightingale.app.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.app.exception.ObjectNotFoundException;

public interface BaseService<T> {

	T create(T t);
	T read(Integer tId);
	T update(T t);
	void delete(Integer tId);
	
	List<T> getListAll();
	
	Pair<List<T>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize) ;

}

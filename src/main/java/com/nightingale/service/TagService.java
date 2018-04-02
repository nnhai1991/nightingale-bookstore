package com.nightingale.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.nightingale.entity.Tag;

public interface TagService {

	Tag create(Tag tag);

	Tag read(String tag);

	Tag update(Tag tag);

	Pair<List<Tag>, Integer> getListWithPaginationBySearch(String keyword, Integer pageNo, Integer pageSize);

	List<Tag> getListAll();

	void delete(String tagId);

	Tag create(String tag, String tagType);

	List<Tag> getTagsByTagType(String tagType);
}

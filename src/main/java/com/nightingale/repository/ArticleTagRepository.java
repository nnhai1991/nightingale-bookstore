package com.nightingale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.entity.ArticleTag;

public interface ArticleTagRepository extends JpaRepository<ArticleTag,Integer> {
	List<ArticleTag> findByArticleIdAndTag_TagType(Integer articleId,String tagType);
	List<ArticleTag> findByArticleId(Integer articleId);
}

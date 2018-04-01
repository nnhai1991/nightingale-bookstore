package com.nightingale.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.ArticleTag;

public interface ArticleTagRepository extends JpaRepository<ArticleTag,Integer> {
	List<ArticleTag> findByArticleId(Integer articleId);
}

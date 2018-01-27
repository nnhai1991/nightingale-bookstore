package com.nightingale.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.ArticleImage;

public interface ArticleImageRepository extends JpaRepository<ArticleImage,Integer> {

	List<ArticleImage> findByArticleId(Integer articleId);
}

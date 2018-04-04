package com.nightingale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nightingale.entity.Article;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
	
	Article findByCode(String code);

	List<Article> findByEnabled(Boolean b);
	@Query("select s from Article s "
    		+ "where (s.name like %:keyword% "
    		+ " or s.code like %:keyword%"
    		+ " or s.description like %:keyword%) ")
	Page<Article> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}

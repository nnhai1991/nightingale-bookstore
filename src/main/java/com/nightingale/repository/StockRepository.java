package com.nightingale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nightingale.entity.Stock;

public interface StockRepository extends JpaRepository<Stock,Integer> {	
	List<Stock> findByArticle_Id(boolean articleId);
	
	@Query("select s from Stock s "
    		+ "where (s.article.name like %:keyword% "
    		+ " or s.fromSite.name like %:keyword%"
    		+ " or s.toSite.name like %:keyword%"
    		+ " or s.type like %:keyword%)")
	Page<Stock> findBySearch(@Param("keyword") String keyword, Pageable pageable);

}

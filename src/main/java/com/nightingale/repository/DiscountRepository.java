package com.nightingale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nightingale.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {		
	@Query("select s from Discount s "
    		+ "where (s.description like %:keyword%"
    		+ " or s.type like %:keyword%)")
	Page<Discount> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}

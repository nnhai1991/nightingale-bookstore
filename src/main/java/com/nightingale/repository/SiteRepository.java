package com.nightingale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nightingale.entity.Site;

public interface SiteRepository extends JpaRepository<Site,Integer> {	
	List<Site> findByEnabled(boolean enabled);
	@Query("select s from Site s "
    		+ "where (s.name like %:keyword%"
    		+ " or s.type like %:keyword%"
    		+ " or s.address like %:keyword%"
    		+ " or s.type like %:keyword%) and s.enabled = true")
	Page<Site> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}

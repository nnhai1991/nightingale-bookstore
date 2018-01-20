package com.nightingale.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nightingale.app.entity.Config;

public interface ConfigRepository extends JpaRepository<Config,Integer> {

	Config findByKey(String key);
	@Query("select s from Config s "
    		+ "where s.key like %:keyword% "
    		+ " or s.value like %:keyword%"
    		+ " or s.description like %:keyword%")
	Page<Config> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}

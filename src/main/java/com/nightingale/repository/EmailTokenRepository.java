package com.nightingale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.entity.EmailToken;


public interface EmailTokenRepository extends JpaRepository<EmailToken,Integer>{

	EmailToken findByToken(String token);
	EmailToken findByEmail(String email);
	
}

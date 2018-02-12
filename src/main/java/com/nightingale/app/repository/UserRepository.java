package com.nightingale.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.nightingale.app.entity.User;

/**
 * @author hai
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    @Query("select u from User u "
    		+ "where u.firstName like %:keyword% "
    		+ " or u.lastName like %:keyword%"
    		+ " or u.email like %:keyword%")
	Page<User> findBySearchWithPage(@Param("keyword") String keyword, Pageable pageable);
}

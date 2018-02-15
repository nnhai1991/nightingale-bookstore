package com.nightingale.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByCode(String ad);
}

package com.nightingale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByCode(String ad);
}

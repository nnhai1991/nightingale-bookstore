package com.nightingale.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nightingale.app.entity.Role;
import com.nightingale.app.exception.ObjectNotFoundException;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByCode(String ad);
}

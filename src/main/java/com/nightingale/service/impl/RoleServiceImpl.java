package com.nightingale.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nightingale.Constants;
import com.nightingale.entity.Role;
import com.nightingale.repository.RoleRepository;
import com.nightingale.service.RoleService;
import com.nightingale.util.web.UtilValidation;

/**
 * Created by hai on 4/7/2017.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role read(Integer id)  {

        if(UtilValidation.isValidId(id))
            return roleRepository.findOne(id);

        return null;
    }

    @Override
    public List<Role> getListAll() {

        return roleRepository.findAll();
    }

    @Override
    public List<Role> getAssignableRoles() {

        List<Role> roles = new LinkedList<>();
        roles.add(roleRepository.findByCode(Constants.Roles.AD));
        return roles;
    }

	@Override
	public Role getRoleByCode(String code) {
		return roleRepository.findByCode(code);
	}

	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}
}

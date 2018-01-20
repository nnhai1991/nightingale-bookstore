package com.nightingale.app.service.impl;

import com.nightingale.app.entity.Role;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.repository.RoleRepository;
import com.nightingale.app.service.RoleService;
import com.nightingale.app.util.UtilConstants;
import com.nightingale.web.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
    public Role read(Integer id) throws ObjectNotFoundException {

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
        roles.add(roleRepository.findByCode(UtilConstants.Roles.AD));
        roles.add(roleRepository.findByCode(UtilConstants.Roles.IN));
        roles.add(roleRepository.findByCode(UtilConstants.Roles.DE));
        roles.add(roleRepository.findByCode(UtilConstants.Roles.PA));
        roles.add(roleRepository.findByCode(UtilConstants.Roles.INDEPA));
        return roles;
    }
}

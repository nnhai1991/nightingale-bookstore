package com.nightingale.app.service;

import com.nightingale.app.entity.Role;
import com.nightingale.app.exception.ObjectNotFoundException;

import java.util.List;

public interface RoleService {

    Role read(Integer id) throws ObjectNotFoundException;

    List<Role> getListAll();

    List<Role> getAssignableRoles();
}

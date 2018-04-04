package com.nightingale.service;

import com.nightingale.entity.Role;


import java.util.List;

public interface RoleService {

    Role read(Integer id) ;

    List<Role> getListAll();

    List<Role> getAssignableRoles();
}

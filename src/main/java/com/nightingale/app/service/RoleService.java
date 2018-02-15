package com.nightingale.app.service;

import com.nightingale.app.entity.Role;


import java.util.List;

public interface RoleService {

    Role read(Integer id) ;

    List<Role> getListAll();

    List<Role> getAssignableRoles();
}

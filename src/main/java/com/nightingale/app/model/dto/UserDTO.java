package com.nightingale.app.model.dto;

import com.nightingale.app.entity.Role;
import com.nightingale.app.entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8090448549867893618L;
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

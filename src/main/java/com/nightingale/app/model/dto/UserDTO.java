package com.nightingale.app.model.dto;

import com.nightingale.app.entity.Role;
import com.nightingale.app.entity.User;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8090448549867893618L;
    private User user;
    private Role role;
}

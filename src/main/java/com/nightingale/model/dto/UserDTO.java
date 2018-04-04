package com.nightingale.model.dto;

import com.nightingale.entity.Role;
import com.nightingale.entity.User;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8090448549867893618L;
    private User user;
    private Role role;
}

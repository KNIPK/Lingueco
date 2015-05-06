package com.lingueco.user.service;

import com.lingueco.user.entity.Role;

import java.util.List;

/**
 * Created by Artur
 */
public interface User_Role_service {

    long createUser_role(Role role);

    List<Role> getAllUsers_roles();

    void deleteUser_role(long id);
}

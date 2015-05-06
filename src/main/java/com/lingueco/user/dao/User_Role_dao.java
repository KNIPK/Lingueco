package com.lingueco.user.dao;

import com.lingueco.user.entity.Role;

import java.util.List;

/**
 * Created by Artur
 */
public interface User_Role_dao {

    long create_User_Role(Role role);

    List getAll_Users_roles();

    public void deleteUser_role(long id);
}

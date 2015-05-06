package com.lingueco.user.service.impl;

import com.lingueco.user.dao.User_Role_dao;
import com.lingueco.user.entity.Role;
import com.lingueco.user.service.User_Role_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Artur
 */
@Service
@Transactional
public class User_Role_service_impl implements User_Role_service{

    @Autowired
    private User_Role_dao user_role_DAO;

    @Override
    public long createUser_role(Role role) {
        return user_role_DAO.create_User_Role(role);
    }

    @Override
    public List<Role> getAllUsers_roles() {
        return user_role_DAO.getAll_Users_roles();
    }

    @Override
    public void deleteUser_role(long id) {
        user_role_DAO.deleteUser_role(id);
    }
}

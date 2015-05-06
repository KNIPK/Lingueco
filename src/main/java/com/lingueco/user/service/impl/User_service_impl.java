package com.lingueco.user.service.impl;

import com.lingueco.user.dao.User_dao;
import com.lingueco.user.entity.User;
import com.lingueco.user.service.User_Role_service;
import com.lingueco.user.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Artur
 */
@Service
@Transactional
public class User_service_impl implements User_service {

    @Autowired
    private User_dao userDAO;


    @Override
    public long createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

}

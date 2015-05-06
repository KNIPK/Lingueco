package com.lingueco.user.dao;

import com.lingueco.user.entity.User;

import java.util.List;

/**
 * Created by Artur
 */
public interface User_dao {

    long createUser(User user);

    User updateUser(User user);

    void deleteUser(long id);

    List getAllUsers();

    User getUser(long id);
}

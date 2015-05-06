package com.lingueco.user.service;

import com.lingueco.user.entity.User;

import java.util.List;

/**
 * Created by Artur
 */
public interface User_service {

    long createUser(User user);

    User updateUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUser(long id);
}

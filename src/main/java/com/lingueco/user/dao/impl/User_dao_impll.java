package com.lingueco.user.dao.impl;

import com.lingueco.user.dao.User_dao;
import com.lingueco.user.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Artur
 */
@Repository
public class User_dao_impll implements User_dao {

    @Autowired
    public SessionFactory sessionFactory;


    public long createUser(User user) {
        Serializable id = sessionFactory.getCurrentSession().save(user);
        return (Long) id;
    }

    public User updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    public void deleteUser(long id) {
        User user = new User();
        user.setUser_id(id);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u from User u");
        return query.list();
    }

    @Override
    public User getUser(long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class,id);
    }
}

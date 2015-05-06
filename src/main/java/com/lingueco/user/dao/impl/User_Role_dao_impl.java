package com.lingueco.user.dao.impl;

import com.lingueco.user.dao.User_Role_dao;
import com.lingueco.user.entity.Role;
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
public class User_Role_dao_impl implements User_Role_dao {

    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public long create_User_Role(Role role) {
        Serializable id = sessionFactory.getCurrentSession().save(role);
        //#############################################################
        return (Long) id;
    }

    @Override
    public List getAll_Users_roles() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u from Role u");
        return query.list();
    }

    public void deleteUser_role(long id) {
        Role role = new Role();
        role.setUser_role_id(id);
        sessionFactory.getCurrentSession().delete(role);
    }
}

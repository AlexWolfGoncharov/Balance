package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.UserRolesDAO;
import com.github.alexwolfgoncharov.balance.security.UserRoles;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */
public class UserRolesDAOImpl implements UserRolesDAO {

    private static final Logger log = Logger.getLogger(UserRolesDAOImpl.class
            .getName());

    @Override
    public void add(UserRoles roles) {
       try{ HibernateMyUtil.getSessionFactory().getCurrentSession()
                .beginTransaction();
        HibernateMyUtil.getSessionFactory().getCurrentSession()
                .saveOrUpdate(roles);
        HibernateMyUtil.getSessionFactory().getCurrentSession()
                .getTransaction().commit();

    } catch (Exception e) {
        HibernateMyUtil.getSessionFactory().getCurrentSession()
                .getTransaction().rollback();
        log.severe(e.getMessage());
    }
    }

    @Override
    public Set<UserRoles> getAll() {

        Set<UserRoles> userRolesSet = new HashSet<UserRoles>();
        List<UserRoles> userRolesList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            userRolesList =  HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(UserRoles.class).list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }


        for (UserRoles role : userRolesList){
            userRolesSet.add(role);
        }
        return userRolesSet;

    }

    @Override
    public void delete(UserRoles roles) {

        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .delete(roles);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

    }
}

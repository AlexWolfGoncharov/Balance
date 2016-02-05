package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.UserDAO;
import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.security.UserRoles;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger log = Logger.getLogger(UserDAOImpl.class
            .getName());


    @Override
    public void add(User user) {
        if(user.getCreateTime() == null){
            user.setCreateTime(new Timestamp(new Date().getTime()));
            user.setLastChangeTime(user.getCreateTime());
        } else {
            user.setLastChangeTime(new Timestamp(new Date().getTime()));
        }

        Session session = null;
        try {
            if(user.getId() == 0){
                user.setId(getNextId());
            }
            session = HibernateMyUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.severe(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

//        try {
//
//            HibernateMyUtil.getSessionFactory().getCurrentSession()
//                    .beginTransaction();
//            HibernateMyUtil.getSessionFactory().getCurrentSession()
//                    .saveOrUpdate(user);
//            HibernateMyUtil.getSessionFactory().getCurrentSession()
//                    .getTransaction().commit();
//
//        } catch (Exception e) {
//            HibernateMyUtil.getSessionFactory().getCurrentSession()
//                    .getTransaction().rollback();
//            log.severe(e.getMessage());
//        }

    }

    @Override
    public User modify(User user) {
        User user2 = null;
        if(user.getCreateTime() == null){
            user.setCreateTime(new Timestamp(new Date().getTime()));
            user.setLastChangeTime(user.getCreateTime());
        } else {
            user.setLastChangeTime(new Timestamp(new Date().getTime()));
        }
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .update(user);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        user2 = getByID(user.getId());
        return user2;
    }

    @Override
    public User getByID(int ID) {
        User user = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            user = (User) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(User.class, ID);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return user;
    }

    @Override
    public User getByLogin(String login) {
        User user = null;

        try {


            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            user = (User) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(User.class)
                    .add(Restrictions.eq("login", login)).uniqueResult();

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {


        List<User> userList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            userList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(User.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return userList;

    }

    @Override
    @Deprecated
    public List<User> getAllByRole(UserRoles role) {
        return null;
    }

    @Override
    public void delete(User user) {



        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .delete(user);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }


    }

    @Override
    public void block(User user) {

        user.setAccess(false);
        add(user);


    }


    public int getNextId() throws SQLException {

        int ret = 0;
        Session session = null;
        try {
            session = HibernateMyUtil.getSessionFactory().openSession();
            List<?> rez = session.createSQLQuery("select max(ID) from Balance.users")
                    .list();
            if (rez != null) {
                ret = Integer.valueOf(rez.get(0).toString());
                ret = ret + 1;
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return ret;
    }

}

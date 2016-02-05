package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.BalanceDAO;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 31.01.16.
 */
public class BalanceDAOImpl implements BalanceDAO {

    private static final Logger log = Logger.getLogger(BalanceDAOImpl.class
            .getName());

    public void add(Balance contract) {
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .saveOrUpdate(contract);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

    }

    public Balance getById(int ID, Object o) {
        Balance contrAgents = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contrAgents = (Balance) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(o.getClass(), ID);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return contrAgents;
    }

    public List<Balance> getAll(Object o) {


        List<Balance> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(o.getClass()).addOrder(Order.asc("id"))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return contractsList;
    }

    public void modify(Balance contract) {
        add(contract);

    }

    public void delete(Balance contract) {



        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .delete(contract);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }


    }
}

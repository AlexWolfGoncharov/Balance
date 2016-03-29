package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ContrAgentsDAO;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 31.01.16.
 */
public class ContrAgentsDAOImpl implements ContrAgentsDAO {

    private static final Logger log = Logger.getLogger(ContrAgentsDAOImpl.class
            .getName());


    @Transactional
    public void add(ContrAgents contract) {
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

    @Transactional
    public ContrAgents getById(int ID) {
        ContrAgents contrAgents = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contrAgents = (ContrAgents) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(ContrAgents.class, ID);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return contrAgents;
    }


    @Transactional
    public List<ContrAgents> getAll() {


        List<ContrAgents> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ContrAgents.class)
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

    @Transactional
    public void modify(ContrAgents contract) {
        add(contract);

    }

    @Transactional
    public void delete(ContrAgents contract) {

        Session session = null;

        try {

            session =  HibernateMyUtil.getSessionFactory().getCurrentSession();

            session.beginTransaction();
            contract = (ContrAgents) session.get(ContrAgents.class, contract.getId());
            session.delete(contract);
            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }


    }
}

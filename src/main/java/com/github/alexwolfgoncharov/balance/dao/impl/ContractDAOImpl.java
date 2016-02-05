package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ContractsDAO;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 31.01.16.
 */
@Repository
public class ContractDAOImpl implements ContractsDAO {

    private static final Logger log = Logger.getLogger(ContractDAOImpl.class
            .getName());

    public void add(Contracts contract) {
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

    public Contracts getById(int ID) {
        Contracts contracts = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contracts = (Contracts) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(Contracts.class, ID);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return contracts;
    }

    public List<Contracts> getAll() {


        List<Contracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(Contracts.class)
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

    public void modify(Contracts contract) {
        add(contract);

    }

    public void delete(Contracts contract) {



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

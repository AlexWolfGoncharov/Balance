package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperContractsDAO;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 31.01.16.
 */
public class ReiceptOperContractsDAOImpl implements ReceiptOperContractsDAO {

    private static final Logger log = Logger.getLogger(ReiceptOperContractsDAOImpl.class
            .getName());


    @Transactional
    public long add(ReceiptOperationsContracts receiptOperationsContractscontract) {
        try {

            if (receiptOperationsContractscontract.getTime() == null)
                receiptOperationsContractscontract.setTime(new Timestamp(new Date().getTime()));
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .saveOrUpdate(receiptOperationsContractscontract);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }
        return receiptOperationsContractscontract.getId();

    }


    @Transactional
    public ReceiptOperationsContracts getById(long ID) {
        ReceiptOperationsContracts contrAgents = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contrAgents = (ReceiptOperationsContracts) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(ReceiptOperationsContracts.class, ID);
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
    public List<ReceiptOperationsContracts> getAll() {


        List<ReceiptOperationsContracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsContracts.class)
                    .addOrder(Order.asc("time"))
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
    public void modify(ReceiptOperationsContracts contract) {
        try {

            if (contract.getTime() == null)
                contract.setTime(new Timestamp(new Date().getTime()));
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
    public void delete(ReceiptOperationsContracts contract) {
        Session session = null;


        try {

            session = HibernateMyUtil.getSessionFactory().getCurrentSession();

                session.beginTransaction();

            ReceiptOperationsContracts  operationsContracts =   (ReceiptOperationsContracts)  session.get(ReceiptOperationsContracts.class, contract.getId());

                session.delete(operationsContracts);
                session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            log.severe(e.getMessage());
        }


    }

    @Transactional
    public List<ReceiptOperationsContracts> getAllByContract(Contracts contract) {
        List<ReceiptOperationsContracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsContracts.class)
                    .add(Restrictions.eq("contractId", contract))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .addOrder(Order.asc("time"))
                    .list();
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
    public List<ReceiptOperationsContracts> getAllForDate(Date start, Date end) {




        if (end == null) {
            java.util.Date e =  new java.util.Date();
                    e.setDate(1);
            end = new java.util.Date(e.getTime());
        }

        java.sql.Date startSql = new java.sql.Date(start.getTime());
        java.sql.Date endSql = new java.sql.Date(end.getTime());

        List<ReceiptOperationsContracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsContracts.class)
                    .add(Restrictions.between("time", startSql, endSql))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .addOrder(Order.asc("time"))
                    .list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return contractsList;
    }
}

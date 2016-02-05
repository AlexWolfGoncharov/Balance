package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperContractsDAO;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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

    public void add(ReceiptOperationsContracts receiptOperationsContractscontract) {
        try {

            if (receiptOperationsContractscontract.getTime() == null)
                receiptOperationsContractscontract.setTime(new Timestamp(new Date().getTime()));
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .save(receiptOperationsContractscontract);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

    }

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

    public List<ReceiptOperationsContracts> getAll() {


        List<ReceiptOperationsContracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsContracts.class)
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

    public void delete(ReceiptOperationsContracts contract) {



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

    public List<ReceiptOperationsContracts> getAllByContract(Contracts contract) {
        List<ReceiptOperationsContracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsContracts.class)
                    .add(Restrictions.eq("contractId", contract))
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
}

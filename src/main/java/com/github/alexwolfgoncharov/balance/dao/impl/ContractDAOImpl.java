package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ContractsDAO;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 31.01.16.
 */
@Repository
public class ContractDAOImpl implements ContractsDAO {

    private static final Logger log = Logger.getLogger(ContractDAOImpl.class
            .getName());
    @Transactional
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
    @Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public List<Contracts> getAllbyContAgent(ContrAgents contrAgents) {
        List<Contracts> contractsList = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contractsList = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(Contracts.class)
                    .add(Restrictions.eq("contrAgentId", contrAgents))
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
    public void modify(Contracts contract) {
        add(contract);

    }

    @Transactional
    public void delete(Contracts contract) {



        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contract = (Contracts) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(Contracts.class, contract.getId());
//            List<ReceiptOperationsContracts> operationsContractsList =
//                    HibernateMyUtil.getSessionFactory().getCurrentSession()
//                            .createCriteria(ReceiptOperationsContracts.class)
//                            .add(Restrictions.eq("contractId", contract))
//                            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                            .addOrder(Order.asc("time"))
//                            .list();
//
//            for (ReceiptOperationsContracts operationsContracts : operationsContractsList){
//
//                HibernateMyUtil.getSessionFactory().getCurrentSession()
//                        .delete(operationsContracts);
//            }

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

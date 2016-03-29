package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperDeptDAO;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
import com.github.alexwolfgoncharov.balance.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
public class ReiceptOperDeptDAOImpl implements ReceiptOperDeptDAO {

    private static final Logger log = Logger.getLogger(ReiceptOperDeptDAOImpl.class
            .getName());



    @Transactional
    public long add(ReceiptOperationsDepartments receiptOperationsDepartments) {
        try {

            if (receiptOperationsDepartments.getTime() == null)
                receiptOperationsDepartments.setTime(new Timestamp(new Date().getTime()));

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .saveOrUpdate(receiptOperationsDepartments);
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();

        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }
        return receiptOperationsDepartments.getId();

    }


    @Transactional
    public ReceiptOperationsDepartments getById(long ID) {
        ReceiptOperationsDepartments contrAgents = null;

        try{
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            contrAgents = (ReceiptOperationsDepartments) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .get(ReceiptOperationsDepartments.class, ID);
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
    public List<ReceiptOperationsDepartments> getAll() {


        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
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

        return receiptOperationsDepartmentses;
    }


    @Transactional
    public void modify(ReceiptOperationsDepartments contract) {
        add(contract);

    }


    @Transactional
    public void delete(ReceiptOperationsDepartments contract) {



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


    @Transactional

    public List<ReceiptOperationsDepartments> getAllbyDept(Departments department) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .add(Restrictions.eq("departmentId", department))
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

        return receiptOperationsDepartmentses;
    }

    @Transactional
    public List<ReceiptOperationsDepartments> getAllByDate(Date start, Date end) {


        if (end == null) {
            java.util.Date e =  new java.util.Date();
            e.setDate(1);
            end = new java.util.Date(e.getTime());
        }

        java.sql.Date startSql = new java.sql.Date(start.getTime());
        java.sql.Date endSql = new java.sql.Date(end.getTime());
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;

        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
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



        return receiptOperationsDepartmentses;
    }

    @Transactional
    public List<ReceiptOperationsDepartments> getAllByDateDepartment(Date start, Date end, Departments departments) {

        if (end == null) {
            java.util.Date e =  new java.util.Date();
            e.setDate(1);
            end = new java.util.Date(e.getTime());
        }

        java.sql.Date startSql = new java.sql.Date(start.getTime());
        java.sql.Date endSql = new java.sql.Date(end.getTime());
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;

        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = ( List<ReceiptOperationsDepartments> ) HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .add(Restrictions.between("time", startSql, endSql))
                    .add(Restrictions.eq("departmentId", departments))
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



        return receiptOperationsDepartmentses;
    }

    @Transactional
    public List<ReceiptOperationsDepartments> getAllByContract(Contracts contracts) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        Session session = null;
        List<ReceiptOperationsContracts> operationsContractses = null;
        try {


            session  = HibernateMyUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

//            session.createCriteria(ReceiptOperationsContracts.class)
//                    .add(Restrictions.eq("contractId", contracts.getId())).list();

//            String query = "SELECT ID FROM receipt_operations_contracts WHERE contract_id = "
//                    + contracts.getId();
//            System.out.println(query);
//           List<BigInteger> idList =( List<BigInteger>) session.createSQLQuery(query).list();
//            List<Long> idLong =   new ArrayList<>();
//            for (BigInteger integer : idList){
//
//                idLong.add(integer.longValue());
//            }
//




            Criteria criteria =   session.createCriteria(ReceiptOperationsDepartments.class, "operdept");

            criteria.setFetchMode("receptOpContrId", FetchMode.JOIN);
            criteria.createAlias("receptOpContrId", "receipt_operations_contracts");
            criteria.add(Restrictions.eq("receipt_operations_contracts.contractId", contracts ));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.addOrder(Order.asc("time"));

            receiptOperationsDepartmentses = ( List<ReceiptOperationsDepartments> )criteria.list();
//
//            receiptOperationsDepartmentses = ( List<ReceiptOperationsDepartments> )
//                    session.createCriteria(ReceiptOperationsDepartments.class)
//                    .add(Restrictions.in("receptOpContrId", idLong ))
//                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                    .addOrder(Order.asc("time"))
//                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            log.severe(e.getMessage());
        }




        return receiptOperationsDepartmentses;


    }

    @Transactional
    public List<ReceiptOperationsDepartments> getAllByContractAndDep(Contracts contracts, Departments departments) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        Session session = null;
        List<ReceiptOperationsContracts> operationsContractses = null;
        try {


            session  = HibernateMyUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();


            Criteria criteria =   session.createCriteria(ReceiptOperationsDepartments.class, "operdept");

            criteria.setFetchMode("receptOpContrId", FetchMode.JOIN);
            criteria.createAlias("receptOpContrId", "receipt_operations_contracts");
            criteria.add(Restrictions.eq("receipt_operations_contracts.contractId", contracts ));
            criteria.add(Restrictions.eq("departmentId", departments));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.addOrder(Order.asc("time"));

            receiptOperationsDepartmentses = ( List<ReceiptOperationsDepartments> )criteria.list();

//
//            String query = "SELECT ID FROM receipt_operations_contracts WHERE contract_id = "
//                    + contracts.getId();
//            System.out.println(query);
//            List<BigInteger> idList =( List<BigInteger>) session.createSQLQuery(query).list();
//            List<Long> idLong =   new ArrayList<>();
//            for (BigInteger integer : idList){
//
//                idLong.add(integer.longValue());
//            }
//
//            receiptOperationsDepartmentses = ( List<ReceiptOperationsDepartments> )
//                    session.createCriteria(ReceiptOperationsDepartments.class)
//                            .add(Restrictions.in("receptOpContrId", idLong))
//                            .add(Restrictions.eq("departmentId", departments))
//                            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                            .addOrder(Order.asc("time"))
//                            .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            log.severe(e.getMessage());
        }




        return receiptOperationsDepartmentses;

    }




    @Transactional

    public List<ReceiptOperationsDepartments> getAllbyOperContr(ReceiptOperationsContracts receiptOperationsContracts) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .add(Restrictions.eq("receptOpContrId", receiptOperationsContracts))
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

        return receiptOperationsDepartmentses;
    }
}

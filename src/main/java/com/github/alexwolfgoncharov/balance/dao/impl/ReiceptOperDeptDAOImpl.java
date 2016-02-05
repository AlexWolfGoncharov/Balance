package com.github.alexwolfgoncharov.balance.dao.impl;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperDeptDAO;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
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
public class ReiceptOperDeptDAOImpl implements ReceiptOperDeptDAO {

    private static final Logger log = Logger.getLogger(ReiceptOperDeptDAOImpl.class
            .getName());

    public void add(ReceiptOperationsDepartments receiptOperationsDepartments) {
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

    }

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

    public List<ReceiptOperationsDepartments> getAll() {


        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return receiptOperationsDepartmentses;
    }

    public void modify(ReceiptOperationsDepartments contract) {
        add(contract);

    }

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

    public List<ReceiptOperationsDepartments> getAllbyDept(Departments department) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .add(Restrictions.eq("departmentId", department))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }

        return receiptOperationsDepartmentses;
    }

    public List<ReceiptOperationsDepartments> getAllbyOperContr(ReceiptOperationsContracts receiptOperationsContracts) {
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;
        try {

            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            receiptOperationsDepartmentses = HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .createCriteria(ReceiptOperationsDepartments.class)
                    .add(Restrictions.eq("receptOpContrId", receiptOperationsContracts))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

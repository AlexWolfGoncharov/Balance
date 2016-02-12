package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperDeptDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ReiceptOperDeptDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
@Service
public class ReceiptOperDeptServiceImpl implements ReceiptOperDeptService{
//    @Autowired
    private static ReceiptOperDeptDAO receiptOperDeptDAO = new ReiceptOperDeptDAOImpl();

    public long add(ReceiptOperationsDepartments contract) {
        return  receiptOperDeptDAO.add(contract);


    }

    public ReceiptOperationsDepartments getById(long ID) {
        return receiptOperDeptDAO.getById(ID);
    }

    public List<ReceiptOperationsDepartments> getAll() {
        return receiptOperDeptDAO.getAll();
    }

    public void modify(ReceiptOperationsDepartments contract) {
        receiptOperDeptDAO.modify(contract);

    }

    public void delete(ReceiptOperationsDepartments contract) {
        receiptOperDeptDAO.delete(contract);

    }

    public List<ReceiptOperationsDepartments> getAllbyDept(Departments department) {
        return receiptOperDeptDAO.getAllbyDept(department);
    }

    @Override
    public List<ReceiptOperationsDepartments> getAllByDate(Date start, Date end) {
        return null;
    }

    @Override
    public List<ReceiptOperationsDepartments> getAllByDateDepartment(Date start, Date end, Departments departments) {
        return null;
    }

    @Override
    public List<ReceiptOperationsDepartments> getAllByContract(Contracts contracts) {
        return null;
    }

    @Override
    public List<ReceiptOperationsDepartments> getAllByContractAndDep(Contracts contracts, Departments departments) {
        return null;
    }

    public List<ReceiptOperationsDepartments> getAllbyOperContr(ReceiptOperationsContracts receiptOperationsContracts) {
        return receiptOperDeptDAO.getAllbyOperContr(receiptOperationsContracts);
    }
}

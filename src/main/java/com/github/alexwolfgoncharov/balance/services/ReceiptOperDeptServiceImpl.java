package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperDeptDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ReiceptOperDeptDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
@Service
public class ReceiptOperDeptServiceImpl implements ReceiptOperDeptService{
//    @Autowired
    private static ReceiptOperDeptDAO receiptOperDeptDAO = new ReiceptOperDeptDAOImpl();

    public void add(ReceiptOperationsDepartments contract) {
        receiptOperDeptDAO.add(contract);

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

    public List<ReceiptOperationsDepartments> getAllbyOperContr(ReceiptOperationsContracts receiptOperationsContracts) {
        return receiptOperDeptDAO.getAllbyOperContr(receiptOperationsContracts);
    }
}

package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;

import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public interface ReceiptOperDeptDAO {
    void add(ReceiptOperationsDepartments contract);
    ReceiptOperationsDepartments getById(long ID);
    List<ReceiptOperationsDepartments> getAll();
    void modify(ReceiptOperationsDepartments contract);
    void delete(ReceiptOperationsDepartments contract);
    List<ReceiptOperationsDepartments> getAllbyDept(Departments department);
    List<ReceiptOperationsDepartments> getAllbyOperContr(ReceiptOperationsContracts receiptOperationsContracts);



}

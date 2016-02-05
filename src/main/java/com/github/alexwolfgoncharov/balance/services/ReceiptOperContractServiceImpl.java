package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ReceiptOperContractsDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ReiceptOperContractsDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
@Service
public class ReceiptOperContractServiceImpl implements ReceiptOperContractService {

//    @Autowired
    private ReceiptOperContractsDAO receiptOperContractsDAO =  new ReiceptOperContractsDAOImpl();

    public void add(ReceiptOperationsContracts contract) {
        receiptOperContractsDAO.add(contract);

    }

    public ReceiptOperationsContracts getById(long ID) {
        return receiptOperContractsDAO.getById(ID);
    }

    public List<ReceiptOperationsContracts> getAll() {
        return receiptOperContractsDAO.getAll();
    }

    public void modify(ReceiptOperationsContracts contract) {
        receiptOperContractsDAO.modify(contract);

    }

    public void delete(ReceiptOperationsContracts contract) {

        List<ReceiptOperationsDepartments> operationsDepartmentsList = contract.getReceiptOperationsDepartmentList();

        ReceiptOperDeptService receiptOperDeptService =  new ReceiptOperDeptServiceImpl();
        for (ReceiptOperationsDepartments operationsDepartments : operationsDepartmentsList){

            receiptOperDeptService.delete(operationsDepartments);
        }

        receiptOperContractsDAO.delete(contract);

    }

    public List<ReceiptOperationsContracts> getAllByContract(Contracts contract) {
        return receiptOperContractsDAO.getAllByContract(contract);
    }
}

package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ContractsDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ContractDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */

@Service
public class ContractsServiceImpl implements  ContractsService {

//    @Autowired
    private static ContractsDAO contractsDAO = new ContractDAOImpl();
    private static ReceiptOperContractService receiptOperContractService  = new ReceiptOperContractServiceImpl();

    public void add(Contracts contract) {

        contractsDAO.add(contract);


    }

    public Contracts getById(int ID) {

        return contractsDAO.getById(ID);
    }

    public List<Contracts> getAll() {
        return contractsDAO.getAll();
    }


    public List<Contracts> getAllbyContAgent(ContrAgents contrAgents) {
        return contractsDAO.getAllbyContAgent(contrAgents);
    }

    public void modify(Contracts contract) {
        contractsDAO.modify(contract);

    }

    public void delete(Contracts contract) {
        List<ReceiptOperationsContracts> operationsContractses = receiptOperContractService.getAllByContract(contract);
        for(ReceiptOperationsContracts operationsContracts : operationsContractses){

            receiptOperContractService.delete(operationsContracts);

        }
        contract.setOperationsContractses(new ArrayList<ReceiptOperationsContracts>());

        contractsDAO.delete(contract);

    }
}

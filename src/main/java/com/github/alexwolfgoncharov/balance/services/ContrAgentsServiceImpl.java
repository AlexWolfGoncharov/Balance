package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ContrAgentsDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ContrAgentsDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */

@Service
public class ContrAgentsServiceImpl  implements  ContrAgentsService{

//    @Autowired
    private static ContrAgentsDAO contrAgentsDAO = new ContrAgentsDAOImpl();
    private static ContractsService contractsService =  new ContractsServiceImpl();
    private static ReceiptOperContractService receiptOperContractService =  new ReceiptOperContractServiceImpl();



    public void add(ContrAgents contract) {
        contrAgentsDAO.add(contract);

    }

    public ContrAgents getById(int ID) {
        return contrAgentsDAO.getById(ID);
    }

    public List<ContrAgents> getAll() {
        return contrAgentsDAO.getAll();
    }

    public void modify(ContrAgents contract) {
        contrAgentsDAO.modify(contract);

    }

    public void delete(ContrAgents contract) {


        List<Contracts> contractsList = contractsService.getAllbyContAgent(contract);
        for (Contracts contracts : contractsList){
            contractsService.delete(contracts);

        }

        contract.setAllContracts(new ArrayList<Contracts>());
        contrAgentsDAO.delete(contract);

    }
}

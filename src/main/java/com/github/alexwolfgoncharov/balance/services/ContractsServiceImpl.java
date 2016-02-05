package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.ContractsDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ContractDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */

@Service
public class ContractsServiceImpl implements  ContractsService {

//    @Autowired
    private ContractsDAO contractsDAO = new ContractDAOImpl();

    public void add(Contracts contract) {

        contractsDAO.add(contract);


    }

    public Contracts getById(int ID) {

        return contractsDAO.getById(ID);
    }

    public List<Contracts> getAll() {
        return contractsDAO.getAll();
    }

    public void modify(Contracts contract) {
        contractsDAO.modify(contract);

    }

    public void delete(Contracts contract) {
        contractsDAO.delete(contract);

    }
}

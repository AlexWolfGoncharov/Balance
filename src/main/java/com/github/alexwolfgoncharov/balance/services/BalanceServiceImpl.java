package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.dao.BalanceDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.BalanceDAOImpl;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 02.02.16.
 */

@Service
public class BalanceServiceImpl implements  BalanceService {

    private static BalanceDAO balanceDAO = new BalanceDAOImpl();

    @Override
    public void add(Balance contract) {
        balanceDAO.add(contract);

    }

    @Override
    public Balance getById(int ID, Object o) {
        return balanceDAO.getById(ID, o);
    }

    @Override
    public List<Balance> getAll(Object o) {
        return balanceDAO.getAll(o);
    }

    @Override
    public void modify(Balance contract) {

        balanceDAO.modify(contract);

    }

    @Override
    public void delete(Balance contract) {
        balanceDAO.delete(contract);

    }
}

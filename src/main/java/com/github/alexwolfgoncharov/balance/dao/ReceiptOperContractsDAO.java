package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;

import java.util.Date;
import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public interface ReceiptOperContractsDAO {
    void add(ReceiptOperationsContracts contract);
    ReceiptOperationsContracts getById(long ID);
    List<ReceiptOperationsContracts> getAll();
    void modify(ReceiptOperationsContracts contract);
    void delete(ReceiptOperationsContracts contract);
    List<ReceiptOperationsContracts> getAllByContract(Contracts contract);
    public List<ReceiptOperationsContracts> getAllForDate(Date start, Date end);



}

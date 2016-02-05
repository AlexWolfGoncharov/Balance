package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
public interface ReceiptOperContractService {
    void add(ReceiptOperationsContracts contract);
    ReceiptOperationsContracts getById(long ID);
    List<ReceiptOperationsContracts> getAll();
    void modify(ReceiptOperationsContracts contract);
    void delete(ReceiptOperationsContracts contract);
    List<ReceiptOperationsContracts> getAllByContract(Contracts contract);
}

package com.github.alexwolfgoncharov.balance.services;

import org.springframework.stereotype.Service;

/**
 * Created by alexwolf on 07.02.16.
 */
@Service
public class ServiceFactory {
    private static BalanceService balanceService;
    private static ReceiptOperContractService receiptOperContractService;
    private static ReceiptOperDeptService receiptOperDeptService;
    private static ServiceFactory factory;

    public static BalanceService getBalanceService() {
        if (balanceService == null) balanceService = new BalanceServiceImpl();
        return balanceService;
    }



    public static ReceiptOperContractService getReceiptOperContractService() {
        if(receiptOperContractService == null) receiptOperContractService = new ReceiptOperContractServiceImpl();
        return receiptOperContractService;
    }

    public static ReceiptOperDeptService getReceiptOperDeptService() {
        if (receiptOperDeptService == null) receiptOperDeptService = new ReceiptOperDeptServiceImpl();
        return receiptOperDeptService;
    }



    public static ServiceFactory getFactory() {
        if (factory == null)  factory = new ServiceFactory();
        return factory;
    }


}

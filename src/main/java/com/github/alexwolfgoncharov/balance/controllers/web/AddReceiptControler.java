package com.github.alexwolfgoncharov.balance.controllers.web;

import com.github.alexwolfgoncharov.balance.services.*;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 02.02.16.
 */

@Controller
public class AddReceiptControler {


    private ReceiptOperDeptService receiptOperDeptService = new ReceiptOperDeptServiceImpl();

    private ReceiptOperContractService receiptOperContractService = new ReceiptOperContractServiceImpl();

    private BalanceService balanceService = new BalanceServiceImpl();


    private static final Logger log = Logger.getLogger(MainController.class
            .getName());

    @RequestMapping(value = "/addbalance", method = RequestMethod.POST)
    public String addContractOperPOST(@ModelAttribute("opercontarct") ReceiptOperationsContracts operationsContracts,
                              BindingResult result, Map<String, Object> map) {

        Contracts currentContr = (Contracts) balanceService.getById(operationsContracts.getContractId().getId(), new Contracts());

//        log.info(operationsContracts.getTime().toString());

        ReceiptOperationsContracts newOperContract = new ReceiptOperationsContracts();
        newOperContract.setContractId(currentContr);
        newOperContract.setSumma(operationsContracts.getSumma());
        newOperContract.setNdc(operationsContracts.getNdc());
        newOperContract.setDescription(operationsContracts.getDescription());
        newOperContract.setTime(operationsContracts.getTime());


        log.info(newOperContract.toString());

//        if (result.hasErrors()) {
//
//            map.put("error","Что-то пошло не так");
//            return "redirect:/addbalance";
//        }

        receiptOperContractService.add(newOperContract);


        return "redirect:/addbalance";
//        return "redirect:/allopercontract";
    }


    @RequestMapping(value = "/addbalance", method = RequestMethod.GET)
    public String addContractOperGET(Map<String, Object> map) {


        List<Balance> balanceList = balanceService.getAll(new  Contracts());
        map.put("contractList", balanceList);

        ReceiptOperationsContracts receiptOperationsContracts =  new ReceiptOperationsContracts();
        map.put("operContract", receiptOperationsContracts);

        return "/addbalance";
    }



    @RequestMapping("/allopercontract")
    public String listContracts(Map<String, Object> map) {


       map.put("operContractList", receiptOperContractService.getAll());
        map.put("contraсtsList", balanceService.getAll(new Contracts()));
        map.put("contragentsList", balanceService.getAll( new ContrAgents()));


        return "allopercontract";
    }


//    @RequestMapping("/delete/contract/{id}")
//    public String deleteContract(@PathVariable("id") Integer id) {
//
//        Contracts contracts =  (Contracts) balanceService.getById(id, new Contracts());
//        balanceService.delete(contracts);
//
//
//        return "redirect:/contracts";
//    }
//
//    @RequestMapping(value = "/edit/contract", method = RequestMethod.POST)
//
//    public String editContractPOST(@ModelAttribute("contract") Contracts contact, BindingResult result) {
//
//
//        ContrAgents contrAgents = (ContrAgents) balanceService.getById(contact.getContrAgentId().getId(),new ContrAgents());
//        contact.setContrAgentId(contrAgents);
//
//
//
//
//        Contracts newContr = (Contracts) balanceService.getById(contact.getId(), new Contracts());
//        newContr.setContractNumber(contact.getContractNumber());
//        newContr.setDescription(contact.getDescription());
//        newContr.setSumm(contact.getSumm());
//        newContr.setContrAgentId(contrAgents);
//        newContr.setStartDate(contact.getStartDate());
//
//
//
////        ContrAgents contrAgent = (ContrAgents) balanceService.getById(contrAgentId, new ContrAgents());
////        contact.setContrAgentId(contrAgent);
//
//        log.info(contact.toString());
//
//        balanceService.modify(newContr);
//
//
//        return "redirect:/contracts";
//    }
//
//
//    @RequestMapping(value = "/edit/contract/{id}", method = RequestMethod.GET)
//    public String editContract(@PathVariable("id") Integer id, Map<String, Object> map) {
//
//        Contracts contracts =  (Contracts) balanceService.getById(id, new Contracts());
//        map.put("contract", contracts);
//        map.put("contraсtsList", balanceService.getAll(contracts));
//        map.put("contragentsList", balanceService.getAll( new ContrAgents()));
//        map.put("save", "../contract");
//
//        return "contracts";
//    }
//


}

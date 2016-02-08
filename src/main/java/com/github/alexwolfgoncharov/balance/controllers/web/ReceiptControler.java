package com.github.alexwolfgoncharov.balance.controllers.web;

import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperDeptService;
import com.github.alexwolfgoncharov.balance.services.ServiceFactory;
import com.github.alexwolfgoncharov.balance.structure.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 02.02.16.
 */

@Controller
public class ReceiptControler {

    private static ServiceFactory serviceFactory = ServiceFactory.getFactory();


    private static ReceiptOperDeptService receiptOperDeptService = ServiceFactory.getFactory().getReceiptOperDeptService();

    private static ReceiptOperContractService receiptOperContractService = ServiceFactory.getFactory().getReceiptOperContractService();

    private static BalanceService balanceService =  ServiceFactory.getFactory().getBalanceService();


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
    public String listContracts(Map<String, Object> map, HttpServletRequest  requestBody) {

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Map<String, String[]> parametrs =  requestBody.getParameterMap();

//        @RequestParam("datestart")Date start, @RequestParam("dateend")Date end,
//        @RequestParam("contractId") int contractId, @RequestParam("contragent") int contragentId


        // 1 - return all list
//        2 - return  list between start and end
//        3 - return  all list for contract
        int typeOfReturn = 0;
        Contracts contracts = null;
        Date start = new Date();
        Date end = new Date();

        if(parametrs.containsKey("all"))
            typeOfReturn = 1;

        if(parametrs.containsKey("datestart")) {
            typeOfReturn =2;

            log.info((String) parametrs.get("datestart")[0]);

           Date st = new Date();
            try {
                st = format.parse(parametrs.get("datestart")[0]);
            } catch (ParseException e) {
                log.warning(e.getMessage());
            }


//            st.setDate(-1);
             start = new Date(st.getTime());

            Date eND = new Date(end.getTime()+ (1000 * 60 * 60 * 24));








            if (parametrs.containsKey("dateend")){


                try {

                    eND = format.parse(parametrs.get("dateend")[0]);
                } catch (ParseException e) {
                    log.warning(e.getMessage());
                }





            }

//            eND.setDate(1);
            end = new Date(eND.getTime());







        }

        if(parametrs.containsKey("contractId")){
            typeOfReturn = 3;
            contracts = (Contracts) balanceService.getById(Integer.parseInt(parametrs.get("contractId")[0]), new Contracts());
        }


        switch (typeOfReturn){
            case 1:
                map.put("operContractList", receiptOperContractService.getAll());
                map.put("tab","all");
                map.put("type", "Все записи");
                break;
            case 2:
                map.put("operContractList", receiptOperContractService.getAllForDate(start, end));
                map.put("type", "За период с " + format.format(start).toString()
                        + " по " + format.format(end).toString());
                map.put("tab","date");
                break;
            case 3:
                map.put("operContractList", receiptOperContractService.getAllByContract(contracts));
                map.put("type", "Для контрата №" + contracts.getContractNumber() + " от " + contracts.getStartDate()
                        + " контрагента: " + contracts.getContrAgentId().getName());
                map.put("tab","contract");
                break;

        }



        map.put("contraсtsList", balanceService.getAll(new Contracts()));
//        map.put("contragentsList", balanceService.getAll(new ContrAgents()));

        if (!map.containsKey("tab"))
            map.put("tab","date");
        return "allopercontract";
    }


    @RequestMapping("/view/opercontract/{id}")
    public String viewContract(@PathVariable("id") long id, Map<String, Object> map) {
        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(id);

        map.put("opercontract", operationsContracts);

        map.put("depList", balanceService.getAll(new Departments()));
        map.put("contractList", balanceService.getAll(new  Contracts()));
        map.put("operdep", new ReceiptOperationsDepartments());


        return "editoper";






    }





    @RequestMapping("/addoperdep")
    public String addOperDep(@ModelAttribute("operdep")ReceiptOperationsDepartments operationsDepartments,
                             BindingResult result, Map<String, Object> map) {

        receiptOperDeptService.add(operationsDepartments);

        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(operationsDepartments.getReceptOpContrId());

        map.put("opercontract", operationsContracts);

        map.put("depList", balanceService.getAll(new Departments()));
        map.put("contractList", balanceService.getAll(new  Contracts()));


        return "redirect:/view/opercontract/"+operationsDepartments.getReceptOpContrId();






    }








    @RequestMapping("/delete/operdep/{id}")
    public String deleteContract(@PathVariable("id") long id) {

        ReceiptOperationsDepartments fordel =  receiptOperDeptService.getById(id);
        receiptOperDeptService.delete(fordel);




        return "redirect:/view/opercontract/"+fordel.getReceptOpContrId();
    }
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

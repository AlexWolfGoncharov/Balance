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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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






//  ===============================
//    Operation for Contracts
//  ================================



    @RequestMapping(value = "/addbalance", method = RequestMethod.POST)
    public String addContractOperPOST(@ModelAttribute("opercontarct") ReceiptOperationsContracts operationsContracts,
                              BindingResult result, Map<String, Object> map) {

        Contracts currentContr = (Contracts) balanceService.getById(operationsContracts.getContractId().getId(), new Contracts());


        ReceiptOperationsContracts newOperContract = null;

        if(operationsContracts.getId() != 0) {
            newOperContract = receiptOperContractService.getById(operationsContracts.getId());

        } else {

            newOperContract = new ReceiptOperationsContracts();

        }

        newOperContract.setContractId(currentContr);
        newOperContract.setSumma(operationsContracts.getSumma());
        newOperContract.setNdc(operationsContracts.getNdc());
        newOperContract.setDescription(operationsContracts.getDescription());
        newOperContract.setTime(operationsContracts.getTime());


      long id =   receiptOperContractService.add(newOperContract);


        return "redirect:/view/opercontract/"+id;

    }


    @RequestMapping(value = "/addbalance", method = RequestMethod.GET)
    public String addContractOperGET(Map<String, Object> map) {


        List<Balance> balanceList = balanceService.getAll(new  Contracts());
        map.put("contractList", balanceList);

        ReceiptOperationsContracts receiptOperationsContracts =  new ReceiptOperationsContracts();
        map.put("operContract", receiptOperationsContracts);

        return "addbalance";
    }



    @RequestMapping("/allopercontract")
    public String listContracts(Map<String, Object> map, HttpServletRequest  requestBody) {

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Map<String, String[]> parametrs =  requestBody.getParameterMap();

//        typeOfReturn:
//        1 - return all list
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
                    eND = new Date(eND.getTime()+ (1000 * 60 * 60 * 24));
                } catch (ParseException e) {
                    log.warning(e.getMessage());
                }



            }

            end = new Date(eND.getTime());

        }

        if(parametrs.containsKey("contractId")){
            typeOfReturn = 3;
            contracts = (Contracts) balanceService.getById(Integer.parseInt(parametrs.get("contractId")[0]), new Contracts());
        }



        List<ReceiptOperationsContracts> receiptOperationsContractsList = new ArrayList<>();

        switch (typeOfReturn){
            case 1:
                receiptOperationsContractsList = receiptOperContractService.getAll();

                map.put("tab","all");
                map.put("type", "Все записи");
                break;
            case 2:
                receiptOperationsContractsList = receiptOperContractService.getAllForDate(start, end);
                map.put("type", "За период с " + format.format(start).toString()
                        + " по " + format.format(end).toString());
                map.put("tab","date");
                break;
            case 3:
                receiptOperationsContractsList =  receiptOperContractService.getAllByContract(contracts);
                map.put("type", "Для контракта №" + contracts.getContractNumber() + " от " + contracts.getStartDate()
                        + "<br/>Контрагент: " + contracts.getContrAgentId().getName());
                map.put("tab","contract");
                break;

        }


        BigDecimal summ = new BigDecimal(0);

        BigDecimal ndc = new BigDecimal(0);

        for (ReceiptOperationsContracts operationsContracts : receiptOperationsContractsList){


            summ = summ.add(operationsContracts.getSumma()) ;

            if (operationsContracts.getNdc() != null)
                ndc = ndc.add(operationsContracts.getNdc());

        }

        map.put("operContractList", receiptOperationsContractsList);
        map.put("summa", summ.floatValue());
        map.put("ndc", ndc.floatValue());
        map.put("contraсtsList", balanceService.getAll(new Contracts()));

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






       @RequestMapping("/edit/opercontract/{id}")
    public String editContractOperGET(@PathVariable("id") long id,
                                      Map<String, Object> map,
                                      HttpServletRequest  requestBody) {


        List<Balance> balanceList = balanceService.getAll(new  Contracts());
        map.put("contractList", balanceList);

        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(id);
        map.put("operContract", operationsContracts);

           if (requestBody.getParameterMap().containsKey("operdep")
                   && !requestBody.getParameterMap().get("operdep").equals("")){

               ReceiptOperationsDepartments receiptOperationsDepartments =
                       receiptOperDeptService.getById(Long.parseLong(requestBody.getParameterMap().get("operdep")[0]));




               map.put("operdep", receiptOperationsDepartments);
           }

        return "addbalance";
    }


    @RequestMapping("/delete/opercontract/{id}")
    public String deleteContractOperGET(@PathVariable("id") long id,
                                        Map<String, Object> map,
                                        HttpServletRequest  requestBody) {



        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(id);

        List<ReceiptOperationsDepartments> operationsDepartmentses = operationsContracts.getReceiptOperationsDepartmentList();

        receiptOperContractService.delete(operationsContracts);

        return "redirect:"+requestBody.getHeader("referer");
    }






//  ===============================
//    Operation for Departaments
//  ================================

    @RequestMapping("/addoperdep")
    public String addOperDep(@ModelAttribute("operdep")ReceiptOperationsDepartments operationsDepartments,
                             BindingResult result, Map<String, Object> map) {
//        Исключаем доваление пустых записей с незаполненной суммой
        if (!operationsDepartments.getSumma().equals(0))
                 receiptOperDeptService.add(operationsDepartments);

        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(operationsDepartments.getReceptOpContrId().getId());

        map.put("opercontract", operationsContracts);

        map.put("depList", balanceService.getAll(new Departments()));
        map.put("contractList", balanceService.getAll(new  Contracts()));


        return "redirect:/view/opercontract/"+operationsDepartments.getReceptOpContrId().getId();






    }






    @RequestMapping("/delete/operdep/{id}")
    public String deleteContract(@PathVariable("id") long id) {

        ReceiptOperationsDepartments fordel =  receiptOperDeptService.getById(id);
        receiptOperDeptService.delete(fordel);




        return "redirect:/view/opercontract/"+fordel.getReceptOpContrId().getId();
    }





//    Getting all ReceiptOperationsDepartments  with filter

    @RequestMapping("/alloperdept")
    public String listRecieptDepartments(Map<String, Object> map, HttpServletRequest  requestBody) {

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Map<String, String[]> parametrs =  requestBody.getParameterMap();

//        typeOfReturn:
//        1 - return all list
//        2 - return  list between start and end  for all Department's
//        3 - return  list between start and end by Department
//        4 - return  all list for contract
//        5 - return  all list for contract by Department
//        6 - return all list by Department

        int typeOfReturn = 0;
        Contracts contracts = null;
        Departments departments = null;
        Date start = new Date();
        Date end = new Date();

        if(parametrs.containsKey("all"))
            typeOfReturn = 1;

        if(parametrs.containsKey("datestart")) {
            typeOfReturn =2;


            Date starT = new Date();
            try {
                starT = format.parse(parametrs.get("datestart")[0]);
            } catch (ParseException e) {
                log.warning(e.getMessage());
            }


//            starT.setDate(-1);
            start = new Date(starT.getTime());

            Date enD = new Date(end.getTime()+ (1000 * 60 * 60 * 24));


            if (parametrs.containsKey("dateend")){


                try {

                    enD = format.parse(parametrs.get("dateend")[0]);

                    enD = new Date(enD.getTime()+ (1000 * 60 * 60 * 24));
                } catch (ParseException e) {
                    log.warning(e.getMessage());
                }



            }

            end = new Date(enD.getTime());

        }

        if(parametrs.containsKey("contractId")){
            typeOfReturn = 4;
            contracts = (Contracts) balanceService.getById(Integer.parseInt(parametrs.get("contractId")[0]), new Contracts());
            log.info(contracts.toString());
        }


        if(parametrs.containsKey("departmentId")){
//            if have date and departmentId
            if (typeOfReturn == 2) {
                typeOfReturn = 3;
//             if have contractID and departmentId
            } else if (typeOfReturn == 4) {
                typeOfReturn = 5;
//             if have only departmentId
            } else {
                typeOfReturn = 6;
            }
            departments = (Departments) balanceService.getById(Integer.parseInt(parametrs.get("departmentId")[0]), new Departments());
            log.info(departments.toString());
        }



        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = new ArrayList<>();


//                typeOfReturn:
//        1 - return all list
//        2 - return  list between start and end  for all Department's
//        3 - return  list between start and end by Department
//        4 - return  all list for contract
//        5 - return  all list for contract by Department
//        6 - return all list by Department

        switch (typeOfReturn){
            case 1:
                receiptOperationsDepartmentses = receiptOperDeptService.getAll();

                map.put("tab","all");
                map.put("type", "Все записи");
                break;
            case 2:
                receiptOperationsDepartmentses = receiptOperDeptService. getAllByDate(start, end);
                map.put("type", "За период с " + format.format(start).toString()
                        + " по " + format.format(end).toString());
                map.put("tab","date");
                break;

            case 3:
                receiptOperationsDepartmentses = receiptOperDeptService.getAllByDateDepartment(start, end, departments);
//                TO-DO
//                 map.put("tab","??");
//                 map.put("type", "??");

                break;
            case 4:
                receiptOperationsDepartmentses =  receiptOperDeptService.getAllByContract(contracts);
                map.put("contract", contracts);
                map.put("tab","contract");
                break;
            case 5:
                receiptOperationsDepartmentses =  receiptOperDeptService.getAllByContractAndDep(contracts, departments);

                map.put("contract", contracts);
                map.put("department", departments);
                 map.put("tab","contract");
                break;
            case 6:
                receiptOperationsDepartmentses =  receiptOperDeptService.getAllbyDept(departments);
                map.put("department", departments);
//                TO-DO
                 map.put("tab","department");
//                 map.put("type", "??");
                break;

        }


        BigDecimal summ = new BigDecimal(0);

        BigDecimal ndc = new BigDecimal(0);


        Map < Departments, ItorOperDepartments> itorOperDepartmentsMap =  new HashMap<>();
        Map <Contracts, ItorOperDepartments> itorOperContractMap = new HashMap<>();
        int count = 0;


         for (ReceiptOperationsDepartments receiptOperationsDepartments : receiptOperationsDepartmentses){
            ItorOperDepartments itorOperForDepartments =  new ItorOperDepartments();
             itorOperForDepartments.setNdc(new BigDecimal(0));
             itorOperForDepartments.setSumma(new BigDecimal(0));
             ItorOperDepartments itorOperForContracts =  new ItorOperDepartments();
             itorOperForContracts.setNdc(new BigDecimal(0));
             itorOperForContracts.setSumma(new BigDecimal(0));


            if (itorOperDepartmentsMap.containsKey(receiptOperationsDepartments.getDepartmentId())){
                itorOperForDepartments = itorOperDepartmentsMap.
                        get(receiptOperationsDepartments.getDepartmentId());
            }

             if (itorOperContractMap.containsKey(receiptOperationsDepartments.getReceptOpContrId().getContractId())){

                 itorOperForContracts = itorOperContractMap.
                         get(receiptOperationsDepartments.
                                 getReceptOpContrId().getContractId());
             }

             itorOperForDepartments.setDepartments(receiptOperationsDepartments.getDepartmentId());
             itorOperForDepartments.setContracts(receiptOperationsDepartments.getReceptOpContrId().getContractId());
             itorOperForDepartments.setCountOfOperations(itorOperForDepartments.getCountOfOperations()+1);
             itorOperForDepartments.setSumma(itorOperForDepartments.getSumma().add(receiptOperationsDepartments.getSumma()));



             itorOperForContracts.setDepartments(receiptOperationsDepartments.getDepartmentId());
             itorOperForContracts.setContracts(receiptOperationsDepartments.getReceptOpContrId().getContractId());
             itorOperForContracts.setCountOfOperations(itorOperForContracts.getCountOfOperations()+1);
             itorOperForContracts.setSumma(itorOperForContracts.getSumma().add(receiptOperationsDepartments.getSumma()));
                 if(receiptOperationsDepartments.getNdc()!= null) {
                     itorOperForDepartments.setNdc(itorOperForDepartments.getNdc()
                                                                         .add(receiptOperationsDepartments.getNdc()));
                     ndc = ndc.add(receiptOperationsDepartments.getNdc());
                     itorOperForContracts.setNdc(itorOperForContracts.getNdc()
                                                                    .add(receiptOperationsDepartments.getNdc()));
                 }




             summ = summ.add(receiptOperationsDepartments.getSumma());

             count++;

             itorOperDepartmentsMap.put(receiptOperationsDepartments.getDepartmentId(),itorOperForDepartments);
             itorOperContractMap.put(receiptOperationsDepartments.getReceptOpContrId().getContractId(), itorOperForContracts);

        }



        List<ItorOperDepartments> forDepartments = new ArrayList<ItorOperDepartments>( itorOperDepartmentsMap.values());
        List<ItorOperDepartments> forContracts = new ArrayList<ItorOperDepartments>( itorOperContractMap.values());



        if (typeOfReturn == 4)
            map.put("itogForContracts", forDepartments);

        if (typeOfReturn == 6)
            map.put("itogForDepartments", forContracts);



        List<Balance> contractsList =  balanceService.getAll(new Contracts());
        List<Balance> departmentsList =  balanceService.getAll(new Departments());

        log.info(forContracts.toString());
        log.info(forDepartments.toString());
        log.info(contractsList.toString());
        log.info(departmentsList.toString());


        map.put("summa", summ);
        map.put("ndc", ndc);
        map.put("count", count);
        map.put("contraсtsList", contractsList);
        map.put("departmentsList", departmentsList);
        map.put("operDepartmentList", receiptOperationsDepartmentses);
        return "alloperdept";
    }






}

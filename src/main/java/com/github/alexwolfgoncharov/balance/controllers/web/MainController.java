package com.github.alexwolfgoncharov.balance.controllers.web;

import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractService;
import com.github.alexwolfgoncharov.balance.services.UserService;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */

@Controller
public class MainController {
    @Autowired
    private ReceiptOperContractService receiptOperContractService;
    @Autowired
    private UserService userService;


    private BalanceService balanceService = new BalanceServiceImpl();


    private static final Logger log = Logger.getLogger(MainController.class
            .getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {

        return "index";
    }

    @RequestMapping(value = "/addusers", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user") User contact,
                             BindingResult result) {

        userService.addUser(contact);

        return "redirect:/userlist";
    }

    @RequestMapping("/userlist")
    public String listUsers(Map<String, Object> map) {

        map.put("user", new User());
        map.put("userList", userService.getAll());

        return "adduser";
    }


    @RequestMapping("/delete/user/{login}")
    public String deleteUser(@PathVariable("id") String login) {

        User user = userService.getUser(login);

        userService.deleteUser(user);


        return "redirect:/userlist";
    }


    @RequestMapping(value = "/adddep", method = RequestMethod.POST)
    public String addDep(@ModelAttribute("department") Departments contact,
                         BindingResult result) {

        balanceService.add(contact);


        return "redirect:/deplist";
    }

    @RequestMapping("/deplist")
    public String listDeps(Map<String, Object> map) {
        Departments dep = new Departments();
        map.put("department", dep);
        map.put("depList", balanceService.getAll(dep));

        return "adddep";
    }


    @RequestMapping("/delete/dep/{id}")
    public String deleteDep(@PathVariable("id") Integer id) {

        Departments dep = (Departments) balanceService.getById(id, new Departments());
        balanceService.delete(dep);


        return "redirect:/deplist";
    }



    @RequestMapping(value = "/addContagent", method = RequestMethod.POST)
    public String addContrAgent(@ModelAttribute("contragent") ContrAgents contact,
                         BindingResult result) {

        balanceService.add(contact);


        return "redirect:/contragents";
    }

    @RequestMapping("/contragents")
    public String listContrAgents(Map<String, Object> map) {
        ContrAgents contrAgents = new ContrAgents();
        map.put("contragent", contrAgents);
        map.put("contragentsList", balanceService.getAll(contrAgents));

        return "contragents";
    }


    @RequestMapping("/delete/contragent/{id}")
    public String deleteContrAgent(@PathVariable("id") Integer id) {

        ContrAgents contrAgents =  (ContrAgents) balanceService.getById(id, new ContrAgents());
        balanceService.delete(contrAgents);


        return "redirect:/contragents";
    }






    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
//    public String addContract(@Valid @ModelAttribute("contract") Contracts contact, @PathParam("contrAgentId") Integer contrAgentId,
//                              BindingResult result) {

    public String addContract(@Valid @ModelAttribute("contract") Contracts contact,
                              BindingResult result, Map<String, Object> map) {

        ContrAgents contrAgents = (ContrAgents) balanceService.getById(contact.getContrAgentId().getId(),new ContrAgents());



        Contracts newContr = new Contracts();
        newContr.setContractNumber(contact.getContractNumber());
        newContr.setDescription(contact.getDescription());
        newContr.setSumm(contact.getSumm());
        newContr.setContrAgentId(contrAgents);
        newContr.setStartDate(contact.getStartDate());


        log.info(newContr.toString());

        if (result.hasErrors()) {

            map.put("error","Что-то пошло не так");
            return "redirect:/contracts";
        }

//        ContrAgents contrAgent = (ContrAgents) balanceService.getById(contrAgentId, new ContrAgents());
//        contact.setContrAgentId(contrAgent);
        balanceService.add(newContr);



        return "redirect:/contracts";
    }

    @RequestMapping("/contracts")
    public String listContracts(Map<String, Object> map) {
        Contracts contracts = new Contracts();
        map.put("save", "addContract");
        map.put("contract", contracts);
        map.put("contraсtsList", balanceService.getAll(contracts));
        map.put("contragentsList", balanceService.getAll( new ContrAgents()));


        return "contracts";
    }


    @RequestMapping("/delete/contract/{id}")
    public String deleteContract(@PathVariable("id") Integer id) {

        Contracts contracts =  (Contracts) balanceService.getById(id, new Contracts());
        balanceService.delete(contracts);


        return "redirect:/contracts";
    }

    @RequestMapping(value = "/edit/contract", method = RequestMethod.POST)

    public String editContractPOST(@ModelAttribute("contract") Contracts contact, BindingResult result) {


        ContrAgents contrAgents = (ContrAgents) balanceService.getById(contact.getContrAgentId().getId(),new ContrAgents());
        contact.setContrAgentId(contrAgents);




        Contracts newContr = (Contracts) balanceService.getById(contact.getId(), new Contracts());
        newContr.setContractNumber(contact.getContractNumber());
        newContr.setDescription(contact.getDescription());
        newContr.setSumm(contact.getSumm());
        newContr.setContrAgentId(contrAgents);
        newContr.setStartDate(contact.getStartDate());



//        ContrAgents contrAgent = (ContrAgents) balanceService.getById(contrAgentId, new ContrAgents());
//        contact.setContrAgentId(contrAgent);

        log.info(contact.toString());

        balanceService.modify(newContr);


        return "redirect:/contracts";
    }


    @RequestMapping(value = "/edit/contract/{id}", method = RequestMethod.GET)
    public String editContract(@PathVariable("id") Integer id, Map<String, Object> map) {

        Contracts contracts =  (Contracts) balanceService.getById(id, new Contracts());
        map.put("contract", contracts);
        map.put("contraсtsList", balanceService.getAll(contracts));
        map.put("contragentsList", balanceService.getAll( new ContrAgents()));
        map.put("save", "../contract");

        return "contracts";

    }











//    @RequestMapping(value = "/receiptcontract/getall")
//    public String getAllEmployeesJSON(Model model)
//    {
//
//
//        model.addAttribute("receiptcontract", receiptOperContractService.getAll());
//        return "jsonTemplate";
//    }


}

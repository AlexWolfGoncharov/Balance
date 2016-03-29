package com.github.alexwolfgoncharov.balance.controllers.web;

import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.security.UserRoles;
import com.github.alexwolfgoncharov.balance.services.*;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */

@Controller
public class MainController {
    @Autowired
    private ReceiptOperContractService receiptOperContractService;
    @Autowired
    private  UserService userService;


    private static BalanceService balanceService = new BalanceServiceImpl();
//    @Autowired
    private static UserRolesService userRolesService = new UserRolesServiceImpl();

    private static ContractsService contractsService =  new ContractsServiceImpl();

    private static ContrAgentsService contrAgentsService = new ContrAgentsServiceImpl();



    private static final Logger log = Logger.getLogger(MainController.class
            .getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {

        return "index";
    }

    @RequestMapping(value = "/addusers", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user") User user,
                             BindingResult result,
                             HttpServletRequest requestBody) {


        Map<String, String[]> parametrs =  requestBody.getParameterMap();


        Set<UserRoles> roles = new HashSet<>();



        for (int i = 0; i < parametrs.get("userRoles").length; i++){
            roles.add( userRolesService.getById(Integer.parseInt(parametrs.get("userRoles")[i])));

        }

        user.setUserRoles(roles);


        userService.addUser(user);

        return "redirect:/userlist";
    }

    @RequestMapping("/userlist")
    public String listUsers(Map<String, Object> map) {

        map.put("user", new User());
        map.put("userList", userService.getAll());

        return "adduser";
    }


    @RequestMapping("/delete/user/{login}")
    public String deleteUser(@PathVariable("login") String login) {

        User user = userService.getUser(login);

        userService.deleteUser(user);


        return "redirect:/userlist";
    }


    @RequestMapping("/edit/user/{login}")
    public String editUser(@PathVariable("login") String login,
                           Map<String, Object> map) {

        User user = userService.getUser(login);
        map.put("user", user);
        map.put("userList", userService.getAll());

        return "adduser";

    }


    @RequestMapping(value = "/adddep", method = RequestMethod.POST)
    public String addDep(@ModelAttribute("department") Departments departments,
                         BindingResult result) {

        if(departments.getId() != 0){
          Departments newDep = (Departments)  balanceService.getById(departments.getId(), departments);
            newDep.setNameOfDepartment(departments.getNameOfDepartment());
            newDep.setDescription(departments.getDescription());
            balanceService.add(newDep);
        }  else{
            balanceService.add(departments);
        }



        return "redirect:/deplist";
    }

    @RequestMapping("/deplist")
    public String listDeps(Map<String, Object> map) {
        Departments dep = new Departments();
        map.put("department", dep);
        map.put("depList", balanceService.getAll(dep));

        return "adddep";
    }


    @RequestMapping("/edit/dep/{id}")
    public String deleteDep(@PathVariable("id") Integer id,
                            Map<String, Object> map) {

        Departments dep = (Departments) balanceService.getById(id, new Departments());
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

        if (contact.getId() != 0){

            ContrAgents cur = (ContrAgents) contrAgentsService.getById(contact.getId());

            cur.setName(contact.getName());
            cur.setAddress(contact.getAddress());

            contrAgentsService.add(cur);

        } else {
            contrAgentsService.add(contact);
        }




        return "redirect:/contragents";
    }

    @RequestMapping("/contragents")
    public String listContrAgents(Map<String, Object> map) {
        ContrAgents contrAgents = new ContrAgents();
        map.put("contragent", contrAgents);
        map.put("contragentsList", contrAgentsService.getAll());

        return "contragents";
    }

    @RequestMapping("/edit/contragent/{id}")
    public String editContrAgent(@PathVariable("id") Integer id,
                                 Map<String, Object> map) {

        ContrAgents contragent =   contrAgentsService.getById(id);

        map.put("contragent", contragent);
        map.put("contragentsList", contrAgentsService.getAll());

        return "contragents";


    }



    @RequestMapping("/delete/contragent/{id}")
    public String deleteContrAgent(@PathVariable("id") Integer id) {

        ContrAgents contrAgents =  (ContrAgents) contrAgentsService.getById(id);
        contrAgentsService.delete(contrAgents);


        return "redirect:/contragents";
    }






    @RequestMapping(value = "/addContract", method = RequestMethod.POST)

    public String addContract(@Valid @ModelAttribute("contract") Contracts contact,
                              BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {

            map.put("error","Что-то пошло не так");
            return "redirect:/contracts";
        }
        ContrAgents contrAgents = (ContrAgents) balanceService.getById(contact.getContrAgentId().getId(),new ContrAgents());


        Contracts newContr = new Contracts();
        newContr.setContractNumber(contact.getContractNumber());
        newContr.setDescription(contact.getDescription());
        newContr.setSumm(contact.getSumm());
        newContr.setContrAgentId(contrAgents);
        newContr.setStartDate(contact.getStartDate());


        log.info(newContr.toString());


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

        Contracts contracts =
                contractsService.getById(id);
        contractsService.delete(contracts);


        return "redirect:/contracts";
    }

    @RequestMapping(value = "/edit/contract", method = RequestMethod.POST)

    public String editContractPOST(@ModelAttribute("contract") Contracts contact,
                                   Map<String, Object> map,
                                   BindingResult result) {

        if (result.hasErrors()) {

            map.put("error","Что-то пошло не так");
            return "redirect:/contracts";
        }
        ContrAgents contrAgents = (ContrAgents) balanceService.getById(contact.getContrAgentId().getId(),new ContrAgents());
        contact.setContrAgentId(contrAgents);




        Contracts newContr = (Contracts) balanceService.getById(contact.getId(), new Contracts());
        newContr.setContractNumber(contact.getContractNumber());
        newContr.setDescription(contact.getDescription());
        newContr.setSumm(contact.getSumm());
        newContr.setContrAgentId(contrAgents);
        newContr.setStartDate(contact.getStartDate());


        log.info(contact.toString());

        balanceService.modify(newContr);


        return "redirect:/contracts";
    }



    @RequestMapping(value = "/edit/contract/{id}", method = RequestMethod.GET)
    public String editContract(@PathVariable("id") Integer id,
                               Map<String, Object> map) {

        Contracts contracts =  (Contracts) balanceService.getById(id, new Contracts());
        map.put("contract", contracts);
        map.put("contraсtsList", balanceService.getAll(contracts));
        map.put("contragentsList", balanceService.getAll( new ContrAgents()));
        map.put("save", "../contract");

        return "contracts";

    }






}

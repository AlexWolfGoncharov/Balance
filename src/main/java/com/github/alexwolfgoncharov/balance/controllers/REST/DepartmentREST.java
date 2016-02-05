package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 01.02.16.
 */

@RestController
public class DepartmentREST {

    private BalanceService balanceService = new BalanceServiceImpl();
    private Departments o = new Departments();
    private  static final Logger log = Logger.getLogger(DepartmentREST.class.getName());



    @RequestMapping(value = "/department/getall")
    public ResponseEntity<List<Balance>> getAllContractReceipts(Model model){
        log.info("Start receiptcontract/getall");
        List<Balance> allUsers = balanceService.getAll(o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<Balance>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/department/{id}")
    public ResponseEntity<Departments> getDepartmentbyID(@PathVariable String id){
        log.info("Start receiptcontract/getall");
        Departments department = (Departments) balanceService.getById(Integer.parseInt(id), o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<Departments>(department, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/department/edit", method = RequestMethod.POST)
    public ResponseEntity<String>editDepartments(@RequestBody Departments input){
                balanceService.modify(input);

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public ResponseEntity<String>addDepartments(@RequestBody Departments input){
        balanceService.add(input);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/department/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String>deleteDepartments(@PathVariable String id){

        Departments depForDel = (Departments) balanceService.getById(Integer.parseInt(id), o);
        log.info("Delete Departments: "+ depForDel.toString());

        balanceService.delete(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }



    }










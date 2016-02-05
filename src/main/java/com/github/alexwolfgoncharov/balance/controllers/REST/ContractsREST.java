package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
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
public class ContractsREST {

    private BalanceService balanceService = new BalanceServiceImpl();
    private Contracts o = new Contracts();
    private  static final Logger log = Logger.getLogger(ContractsREST.class.getName());



    @RequestMapping(value = "/contracts/getall")
    public ResponseEntity<List<Balance>> getAllContracts(Model model){
        log.info("Start receiptcontract/getall");
        List<Balance> allUsers = balanceService.getAll(o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<Balance>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contracts/{id}")
    public ResponseEntity<Contracts> getContractbyID(@PathVariable String id){
        log.info("Start receiptcontract/getall");
        Contracts contracts = (Contracts) balanceService.getById(Integer.parseInt(id), o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<Contracts>(contracts, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/contracts/edit", method = RequestMethod.POST)
    public ResponseEntity<String>editContract(@RequestBody Contracts input){
                balanceService.modify(input);

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contracts/add", method = RequestMethod.POST)
    public ResponseEntity<String>addContract(@RequestBody Contracts input){
        balanceService.add(input);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contracts/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String>deleteContract(@PathVariable String id){

        Departments depForDel = (Departments) balanceService.getById(Integer.parseInt(id), o);
        log.info("Delete Departments: "+ depForDel.toString());

        balanceService.delete(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }



    }










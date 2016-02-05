package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
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
public class ContrAgentREST {

    private BalanceService balanceService = new BalanceServiceImpl();
    private ContrAgents o = new ContrAgents();
    private  static final Logger log = Logger.getLogger(ContrAgentREST.class.getName());



    @RequestMapping(value = "/contragents/getall")
    public ResponseEntity<List<Balance>> getAllContracts(Model model){
        log.info("Start /contragents/getall");
        List<Balance> allUsers = balanceService.getAll(o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<Balance>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contragents/{id}")
    public ResponseEntity<ContrAgents> getContractbyID(@PathVariable String id){
        log.info("Start receiptcontract/getall");
        ContrAgents contracts = (ContrAgents) balanceService.getById(Integer.parseInt(id), o);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ContrAgents>(contracts, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/contragents/edit", method = RequestMethod.POST)
    public ResponseEntity<ContrAgents>editContract(@RequestBody ContrAgents input){
                balanceService.modify(input);
        ContrAgents contrAgents = (ContrAgents) balanceService.getById(input.getId(), o);

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ContrAgents>(contrAgents, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contragents/add", method = RequestMethod.POST)
    public ResponseEntity<String>addContract(@RequestBody ContrAgents input){
        balanceService.add(input);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/contragents/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String>deleteContract(@PathVariable String id){

        ContrAgents depForDel = (ContrAgents) balanceService.getById(Integer.parseInt(id), o);
        log.info("Delete ContrAgents: "+ depForDel.toString());

        balanceService.delete(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }



    }










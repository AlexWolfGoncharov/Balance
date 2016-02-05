package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReceiptContractREST {
    @Autowired
    private ReceiptOperContractService receiptOperContractService = new ReceiptOperContractServiceImpl();
    private  static final Logger log = Logger.getLogger(ReceiptContractREST.class.getName());



    @RequestMapping(value = "/receiptcontract/getall")
    public ResponseEntity<List<ReceiptOperationsContracts>> getAllContractReceipts(Model model){
        log.info("Start receiptcontract/getall");
        List<ReceiptOperationsContracts> allUsers = receiptOperContractService.getAll();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<ReceiptOperationsContracts>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/receiptcontract/{id}")
    public ResponseEntity<ReceiptOperationsContracts> getContractReceiptbyID(@PathVariable String id){
        log.info("Start receiptcontract/getall");
        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(Long.parseLong(id));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ReceiptOperationsContracts>(operationsContracts, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/receiptcontract/add", method = RequestMethod.POST)
    public ResponseEntity<String>editUsers(@RequestBody ReceiptOperationsContracts input){
                receiptOperContractService.add(input);

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/receiptcontract/edit", method = RequestMethod.POST)
    public ResponseEntity<ReceiptOperationsContracts>editContract(@RequestBody ReceiptOperationsContracts input){
        receiptOperContractService.modify(input);
        ReceiptOperationsContracts receiptOperationsContracts = receiptOperContractService.getById(input.getId());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ReceiptOperationsContracts>(receiptOperationsContracts, responseHeaders, HttpStatus.OK);


    }


    @RequestMapping(value = "/receiptcontract/delete/{id}")
    public ResponseEntity<String> deleteContractReceiptbyID(@PathVariable String id){
        log.info("Start receiptcontract/delete/"+id);
        ReceiptOperationsContracts operationsContracts = receiptOperContractService.getById(Long.parseLong(id));
        receiptOperContractService.delete(operationsContracts);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }


    }










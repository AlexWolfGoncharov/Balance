package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.services.ReceiptOperDeptService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperDeptServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;
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
public class ReceiptDepartmREST {
    @Autowired
    private ReceiptOperDeptService receiptOperDeptService = new ReceiptOperDeptServiceImpl();
    private  static final Logger log = Logger.getLogger(ReceiptDepartmREST.class.getName());



    @RequestMapping(value = "/receiptdepartment/getall")
    public ResponseEntity<List<ReceiptOperationsDepartments>> getAllContractReceipts(Model model){
        log.info("Start receiptdepartment/getall");
        List<ReceiptOperationsDepartments> allUsers = receiptOperDeptService.getAll();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<ReceiptOperationsDepartments>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/receiptdepartment/{id}")
    public ResponseEntity<ReceiptOperationsDepartments> getContractReceiptbyID(@PathVariable String id){
        log.info("Start receiptdepartment/{id}");
        ReceiptOperationsDepartments operationsContracts = receiptOperDeptService.getById(Long.parseLong(id));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ReceiptOperationsDepartments>(operationsContracts, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/receiptdepartment/add", method = RequestMethod.POST)
    public ResponseEntity<String>editUsers(@RequestBody ReceiptOperationsDepartments input){
                receiptOperDeptService.add(input);

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }


    @RequestMapping(value = "/receiptdepartment/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String>deleteContract(@PathVariable String id){

        ReceiptOperationsDepartments depForDel =  receiptOperDeptService.getById(Long.parseLong(id));
        log.info("Delete ContrAgents: "+ depForDel.toString());

        receiptOperDeptService.delete(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }


    @RequestMapping(value = "/receiptdepartment/edit", method = RequestMethod.POST)
    public ResponseEntity<ReceiptOperationsDepartments>editContract(@RequestBody ReceiptOperationsDepartments input){
        receiptOperDeptService.modify(input);
        ReceiptOperationsDepartments contrAgents = receiptOperDeptService.getById(input.getId());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<ReceiptOperationsDepartments>(contrAgents, responseHeaders, HttpStatus.OK);


    }

    }










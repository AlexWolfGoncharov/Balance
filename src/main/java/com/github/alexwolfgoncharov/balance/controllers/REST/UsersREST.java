package com.github.alexwolfgoncharov.balance.controllers.REST;

import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.services.UserService;
import com.github.alexwolfgoncharov.balance.services.UserServiceImpl;
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
public class UsersREST {

    private UserService userService = new UserServiceImpl();

    private  static final Logger log = Logger.getLogger(UsersREST.class.getName());



    @RequestMapping(value = "/users/getall")
    public ResponseEntity<List<User>> getAllUsers(Model model){
        log.info("Start /users/getall");
        List<User> allUsers = userService.getAll();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<List<User>>(allUsers, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/users/{login}")
    public ResponseEntity<User> getUserbyID(@PathVariable String login){
        log.info("Start users/{id}");
        User contracts = userService.getUser(login);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<User>(contracts, responseHeaders, HttpStatus.OK);


    }





    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public ResponseEntity<User>editUser(@RequestBody User input){
        userService.update(input);
        User user = userService.getUser(input.getUsername());

                HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<User>(user, responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<String>addUser(@RequestBody User input){
        userService.addUser(input);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/users/delete/{login}", method = RequestMethod.POST)
    public ResponseEntity<String>deleteUser(@PathVariable String login){

        User depForDel = userService.getUser(login);
        log.info("Delete User: "+ depForDel.toString());

        userService.deleteUser(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }

    @RequestMapping(value = "/users/block/{login}", method = RequestMethod.POST)
    public ResponseEntity<String> blockUser(@PathVariable String login){

        User depForDel = userService.getUser(login);
        log.info("Block User: "+ depForDel.toString());

        userService.block(depForDel);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*"); // also added header to allow cross domain request for any domain
        return new ResponseEntity<String>("{\"status\":\"ok\"}", responseHeaders, HttpStatus.OK);


    }



    }










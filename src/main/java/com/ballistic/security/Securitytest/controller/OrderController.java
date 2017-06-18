package com.ballistic.security.Securitytest.controller;

import com.ballistic.security.Securitytest.model.Order;
import com.ballistic.security.Securitytest.service.FectchDataService;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lycus 01 on 6/18/2017.
 */
@RestController
public class OrderController {

    @Autowired
    private FectchDataService fectchDataService;

    private List<Order> orders;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> readData(){

        orders  = fectchDataService.readData();
        if(orders.isEmpty()){
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {

        // delete the item from the user
        orders  = fectchDataService.readData();
        for (Order order : orders) {
            if(order.getId() == id){
                return new ResponseEntity<Order>(order,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<Void> newOrder() {

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrder(@PathVariable("id") Long id) {

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/homePage",method = RequestMethod.GET)
    public ResponseEntity<Void> homePage() {

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }



}

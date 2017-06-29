package com.ballistic.security.Securitytest.service;

import com.ballistic.security.Securitytest.SecuritytestApplication;
import com.ballistic.security.Securitytest.model.Order;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ballistic Inc on 6/18/2017.
 */
@Service
public class FectchDataService {

    public List<Order> orderList = new ArrayList<>();

    public List<Order> readData() {

        JSONParser parser = new JSONParser();
        JSONArray item$List = null;
        try {
            System.out.println("----------------------");
            File file = ResourceUtils.getFile("classpath:read.json");
            //File is found
            System.out.println("File Found : " + file.exists());
            //Get file from resources folder
            item$List = (JSONArray) parser.parse(new FileReader(file));
            System.out.println(item$List);
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // after that we put this object into the list
        for (Object object : item$List){

            JSONObject item$ = (JSONObject) object;
            Order order = new Order((Long) item$.get("id"), (String) item$.get("name"), (Long) item$.get("qty"),(Double)item$.get("rate"), (Double)item$.get("amount"));
            System.out.println(order);
            orderList.add(order);
        }
        return orderList;
    }
}

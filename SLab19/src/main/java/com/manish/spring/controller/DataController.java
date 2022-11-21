package com.manish.spring.controller;

import com.manish.spring.model.DBDetails;
import com.manish.spring.model.IPDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataController {

    private DBDetails dbDetails;
    private IPDetails ipDetails;

    @Autowired
    public DataController(DBDetails dbDetails, IPDetails ipDetails) {
        this.dbDetails = dbDetails;
        this.ipDetails = ipDetails;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String readData(){
        System.out.println("DataController -> showDetails");
        System.out.println(dbDetails);
        System.out.println(ipDetails);
        return "Check your server log";
    }


}

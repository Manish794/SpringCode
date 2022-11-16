package com.manish.spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HaiController {

    @GetMapping(value = "/hai")
    public String hai(){
        System.out.println("-- HaiController.hai()--");
        return "Hai Guys ! Welcome to Spring Boot Application";
    }

    @RequestMapping(value = "/hai/{visitorName}", method = RequestMethod.GET)
    public String haiWithName(@PathVariable("visitorName")  String nm){
        System.out.println("-- HaiController.haiWithName()--");
        return "Hai "+nm+" ! Welcome to Spring Boot Application";
    }
}
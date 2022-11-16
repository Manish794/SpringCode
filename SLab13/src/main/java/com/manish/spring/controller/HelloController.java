package com.manish.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        System.out.println("-- HelloController.home()--");
        return "Welcome to Spring Boot Application";
    }

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        System.out.println("-- HelloController.hello()--");
        return "Hello Guys ! Welcome to Spring Boot Application";
    }

    @ResponseBody
    @RequestMapping(value = "/hello/{visitorName}", method = RequestMethod.GET)
    public String helloWithName(@PathVariable("visitorName")  String nm){
        System.out.println("-- HelloController.helloWithName()--");
        return "Hello "+nm+" ! Welcome to Spring Boot Application";
    }

}

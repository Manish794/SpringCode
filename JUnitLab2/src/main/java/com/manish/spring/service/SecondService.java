package com.manish.spring.service;

public class SecondService {
    String prepareMessage(String name){
        if(name == null || name.trim().length() == 0)
            return null;
        return "Hello "+ name.trim();
    }
}

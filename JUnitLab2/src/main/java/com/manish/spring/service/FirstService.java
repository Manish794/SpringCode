package com.manish.spring.service;

public class FirstService {

    int findLength(String name){
        if(name == null || name.trim().length() == 0)
            return -1;
        return name.length();
    }

    boolean isEmpty(String name){
        if(name == null || name.trim().length() == 0)
            return true;
        return false;
    }

}

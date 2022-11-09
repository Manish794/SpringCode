package com.manish.spring.domain;

import lombok.Data;

@Data
public class Employee {

    private int eid;
    private String ename;
    public Employee(){
        System.out.println("\n-- Employee() Called ");
    }

    public void showEmployeeDetails(){
        System.out.println("-- showEmployeeDetails() Called ");
        System.out.println("Eid "+ eid +", EName "+ename);
    }
}

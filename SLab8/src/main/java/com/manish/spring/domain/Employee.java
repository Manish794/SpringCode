package com.manish.spring.domain;

public class Employee {

    private int eid;
    private String ename;
    public Employee(){
        System.out.println("-- Employee() Called ");
    }

    public void setEid(int eid) {
        System.out.println("-- setEid() Called ");
        this.eid = eid;
    }

    public void setEname(String ename) {
        System.out.println("-- setEname() Called ");
        this.ename = ename;
    }

    public void showEmployeeDetails(){
        System.out.println("-- showEmployeeDetails() Called ");
        System.out.println("Eid "+ eid +", EName "+ename);
    }
}

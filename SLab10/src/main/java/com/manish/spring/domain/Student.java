package com.manish.spring.domain;

import lombok.Data;

@Data
public class Student {

    private int sid;
    private String sname;

    public Student(){
        System.out.println("** Student() Called **");
    }

    public void showStudentDetails(){
        System.out.println("** showStudentDetails() Called **");
        System.out.println("Sid "+ sid +", SName "+sname);
    }

}

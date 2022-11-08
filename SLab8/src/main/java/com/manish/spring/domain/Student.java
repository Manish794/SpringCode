package com.manish.spring.domain;

public class Student {

    private int sid;
    private String sname;

    public Student(){
        System.out.println("** Student() Called **");
    }

    public void setSid(int sid) {
        System.out.println("** setSid() Called **");
        this.sid = sid;
    }

    public void setSname(String sname) {
        System.out.println("** setSname() Called **");
        this.sname = sname;
    }

    public void showStudentDetails(){
        System.out.println("** showStudentDetails() Called **");
        System.out.println("Sid "+ sid +", SName "+sname);
    }

}

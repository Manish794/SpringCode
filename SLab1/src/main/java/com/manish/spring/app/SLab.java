package com.manish.spring.app;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;

public class SLab {
    public static void main(String[] args) {
        Student st1 = new Student();
        st1.setSid(101);
        st1.setSname("Manish");
        st1.showStudentDetails();

        Employee em1 = new Employee();
        em1.setEid(1001);
        em1.setEname("Kumar");
        em1.showEmployeeDetails();

    }

}

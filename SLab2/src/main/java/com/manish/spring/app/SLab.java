package com.manish.spring.app;

import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SLab {
    public static void main(String[] args) {

        ApplicationContext springContainer = new ClassPathXmlApplicationContext("my-spring-config.xml");
        System.out.println("-- Spring Container is ready/initialized --");

        // This code is fine for Single Bean of Student
        Student st1 = springContainer.getBean(Student.class);
        st1.showStudentDetails();

        // This code is fine for Single Bean of Employee
        Employee em1 = springContainer.getBean(Employee.class);
        em1.showEmployeeDetails();

        // This code is fine for Multiple Bean of Student to get the specified bean
        Student st2 = springContainer.getBean("s2", Student.class);
        st2.showStudentDetails();

    }

}

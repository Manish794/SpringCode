package com.manish.spring.app;

import com.manish.spring.config.MyEmployeeConfig;
import com.manish.spring.config.MyStudentConfig;
import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SLab {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyStudentConfig.class, MyEmployeeConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        Student st1 = context.getBean("stud", Student.class);
        st1.showStudentDetails();

        Employee e1 = context.getBean("emp", Employee.class);
        e1.showEmployeeDetails();

        Student st2 = context.getBean("mystud", Student.class);
        st2.showStudentDetails();

        Employee e2 = context.getBean("myemp", Employee.class);
        e2.showEmployeeDetails();

        Student st3 = context.getBean(Student.class);
        st3.showStudentDetails();

        Employee e3 = context.getBean(Employee.class);
        e3.showEmployeeDetails();


    }
}

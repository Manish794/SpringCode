package com.manish.spring.app;

import com.manish.spring.config.MyConfig;
import com.manish.spring.domain.Employee;
import com.manish.spring.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SLab {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        Student st = context.getBean(Student.class);
        st.showStudentDetails();

        Employee emp1 = context.getBean(Employee.class);
        emp1.setEid(9001);
        emp1.setEname("FirstEmployee");
        emp1.showEmployeeDetails();

        Employee emp2 = context.getBean(Employee.class);
        emp2.setEid(9002);
        emp2.setEname("SecondEmployee");

        System.out.println("\nWith emp1");
        emp1.showEmployeeDetails();
        System.out.println("\nWith emp2");
        emp2.showEmployeeDetails();

    }
}

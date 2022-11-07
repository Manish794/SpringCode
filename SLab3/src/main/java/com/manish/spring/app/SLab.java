package com.manish.spring.app;

import com.manish.spring.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SLab {
    public static void main(String[] args) {

        ApplicationContext springContainer = new ClassPathXmlApplicationContext("my-spring-config.xml");
        System.out.println("-- Spring Container is ready/initialized --");

        Student st1 = springContainer.getBean("s1", Student.class);
        st1.showStudentDetails();

        Student st2 = springContainer.getBean("s2", Student.class);
        st2.showStudentDetails();

        Student st3 = springContainer.getBean("s3", Student.class);
        st3.showStudentDetails();

        Student st4 = springContainer.getBean("s4", Student.class);
        st4.showStudentDetails();

        Student st5 = springContainer.getBean("s5", Student.class);
        st5.showStudentDetails();

        Student st6 = springContainer.getBean("s6", Student.class);
        st6.showStudentDetails();

        Student st7 = springContainer.getBean("s7", Student.class);
        st7.showStudentDetails();

    }

}

package com.manish.spring.app;

import com.manish.spring.domain.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SLab {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("my-employee-config.xml", "my-student-config.xml", "my-service-config.xml");
        System.out.println("-- Spring Container is ready/initialized --");

        Service sr1 = context.getBean("serv1", Service.class);
        sr1.showDetails();

        Service sr2 = context.getBean("serv2", Service.class);
        sr2.showDetails();

    }

}

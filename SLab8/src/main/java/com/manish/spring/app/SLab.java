package com.manish.spring.app;

import com.manish.spring.config.MyEmployeeConfig;
import com.manish.spring.config.MyServiceConfig;
import com.manish.spring.config.MyStudentConfig;
import com.manish.spring.domain.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SLab {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyStudentConfig.class, MyEmployeeConfig.class, MyServiceConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        Service sr1 = context.getBean("serv1", Service.class);
        sr1.showDetails();

        Service sr2 = context.getBean("serv2", Service.class);
        sr2.showDetails();

        Service sr3 = context.getBean("serv3", Service.class);
        sr3.showDetails();

        Service sr4 = context.getBean("serv4", Service.class);
        sr4.showDetails();

        Service sr5 = context.getBean("serv5", Service.class);
        sr5.showDetails();


    }
}

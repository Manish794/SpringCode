package com.manish.spring.app;

import com.manish.spring.config.MyConfig;
import com.manish.spring.domain.DBDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SLab {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        DBDetails dbDetails = context.getBean("dbDetailsUsingValue", DBDetails.class);
        System.out.println(dbDetails);

        DBDetails dbDetailsWithEnv = context.getBean("dbDetailsUsingEnvironment", DBDetails.class);
        System.out.println(dbDetailsWithEnv);

    }
}

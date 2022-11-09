package com.manish.spring.app;

import com.manish.spring.config.MyConfig;
import com.manish.spring.service.FirstService;
import com.manish.spring.service.SecondService;
import com.manish.spring.service.ThirdService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SLab {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        FirstService firstService = context.getBean(FirstService.class);
        firstService.showDetails();

        SecondService secondService = context.getBean(SecondService.class);
        secondService.showDetails();

        ThirdService thirdService = context.getBean(ThirdService.class);
        thirdService.showDetails();

    }
}

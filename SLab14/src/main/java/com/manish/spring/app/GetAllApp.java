package com.manish.spring.app;

import com.manish.spring.config.MyConfig;
import com.manish.spring.controller.UserController;
import com.manish.spring.domain.User;
import com.manish.spring.domain.UserResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class GetAllApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        UserController userController = context.getBean(UserController.class);
        UserResponse<List<User>> response = userController.getAllUsers();
        System.out.println(response);
    }
}

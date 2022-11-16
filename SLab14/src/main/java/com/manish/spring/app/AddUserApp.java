package com.manish.spring.app;

import com.manish.spring.config.MyConfig;
import com.manish.spring.controller.UserController;
import com.manish.spring.domain.User;
import com.manish.spring.domain.UserResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AddUserApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("-- Spring Container is ready/initialized --");

        UserController userController = context.getBean(UserController.class);

        User user = new User();
        user.setFullName("Manish");
        user.setEmail("manish@tbaba.com");
        user.setPhone(98989898L);
        user.setLoginId("manish@11");
        user.setLoginPassword("abcd1234");
        UserResponse<String> response = userController.addUser(user);

        System.out.println(response);
        response = userController.addUser(user);
        System.out.println(response);
    }
}

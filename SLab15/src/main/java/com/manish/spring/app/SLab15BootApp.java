package com.manish.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.manish.spring")
public class SLab15BootApp {
    public static void main(String[] args) {
        SpringApplication.run(SLab15BootApp.class, args);
    }
}

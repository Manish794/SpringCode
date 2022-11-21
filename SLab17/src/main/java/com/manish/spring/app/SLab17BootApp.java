package com.manish.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.manish.spring")
@EntityScan("com.manish.spring.jpa.entity")
public class SLab17BootApp {
    public static void main(String[] args) {
        SpringApplication.run(SLab17BootApp.class, args);
    }
}

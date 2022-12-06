package com.manish.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.manish.spring")
@EntityScan("com.manish.spring.jpa.entity")
@EnableJpaRepositories("com.manish.spring.repo")
public class SLab23BootApp {
    public static void main(String[] args) {
        SpringApplication.run(SLab23BootApp.class, args);
    }
}

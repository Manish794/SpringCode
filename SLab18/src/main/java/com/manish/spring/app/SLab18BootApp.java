package com.manish.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.manish.spring", exclude = {DataSourceAutoConfiguration.class})
public class SLab18BootApp {
    public static void main(String[] args) {
        SpringApplication.run(SLab18BootApp.class, args);
    }
}

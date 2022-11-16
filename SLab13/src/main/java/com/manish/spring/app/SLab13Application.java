package com.manish.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.manish.spring.controller" )
public class SLab13Application {

	public static void main(String[] args) {
		SpringApplication.run(SLab13Application.class, args);
	}

}
